package com.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


 open class Login: AppCompatActivity() {

     lateinit var binding: ActivityLoginBinding
     lateinit var gsc: GoogleSignInClient
     lateinit var auth: FirebaseAuth //layout used in this activity
     val firebaseUser = FirebaseAuth.getInstance().currentUser
     val uid = firebaseUser?.uid

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         binding = ActivityLoginBinding.inflate(layoutInflater)
         setContentView(binding.root)

         auth = FirebaseAuth.getInstance()

         val gso: GoogleSignInOptions =
             GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                 .requestIdToken(getString(R.string.default_web_client_id))
                 .requestEmail()
                 .build()

         gsc = GoogleSignIn.getClient(this, gso)
         val firebaseUser = FirebaseAuth.getInstance().currentUser
         val uid = firebaseUser?.uid


         binding.pwForgotten.setOnClickListener(){
             var intent = Intent(this, ChangePassword::class.java)
             startActivity(intent)
         }

         binding.buttonGmail.setOnClickListener() {
             signIn()
         }

         binding.Login.setOnClickListener {
             signInUser()
         }
     }

     fun out() {
         gsc.signOut()
             .addOnCompleteListener(this, OnCompleteListener {
                 signout()
             })
     }

     private fun signout() {
         Toast.makeText(this@Login, "deconnect", Toast.LENGTH_SHORT).show()
     }

     private fun signIn() {
         var singinintent: Intent = gsc.signInIntent
         launcher.launch(singinintent)

     }

     private val launcher =
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

                 result ->

             if (result.resultCode == Activity.RESULT_OK) {

                 val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                 handleResults(task)
             }
         }

     private fun handleResults(task: Task<GoogleSignInAccount>) {

         if (task.isSuccessful) {

             val account: GoogleSignInAccount = task.result
             if (account != null) {
                 updateUI(account)
             }

         } else {

             Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
         }

     }

     private fun updateUI(account: GoogleSignInAccount) {

         val credential = GoogleAuthProvider.getCredential(account.idToken, null)
         auth.signInWithCredential(credential).addOnCompleteListener {
             if (it.isSuccessful) {
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
             } else {
                 Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
             }
         }
     }

     private fun updateUI(user: FirebaseUser?) {
         val intent = Intent(this, MainActivity::class.java)
         startActivity(intent)
     }


     private fun signInUser() {
         var email = binding.email.text.toString().trim()
         var pass = binding.pass.text.toString().trim()

         if (email.isNotEmpty()) {
             auth.signInWithEmailAndPassword(email, pass)
                 .addOnCompleteListener { signIn ->
                     if (signIn.isSuccessful) {
                         startActivity(Intent(this, MainActivity::class.java))
                         Toast.makeText(
                             baseContext, "Authentication reussi.",
                             Toast.LENGTH_SHORT
                         ).show()
                     } else {
                         Log.d("test", "raté")
                         Toast.makeText(
                             baseContext, "Authentication foiré.",
                             Toast.LENGTH_SHORT
                         ).show()
                     }
                 }
             }
        }
    }





