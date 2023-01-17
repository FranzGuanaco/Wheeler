package com.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.databinding.ActivityCreateAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.example.wheeler.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

open class Create_account: AppCompatActivity() {

    lateinit var binding: ActivityCreateAccountBinding
    lateinit var gsc: GoogleSignInClient
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonPhone.setOnClickListener(){
            var intent = Intent(this, PhoneNumber::class.java)
            startActivity(intent)
        }

        binding.buttonGmail.setOnClickListener() {
            signIn()
        }


        val gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

         gsc = GoogleSignIn.getClient(this, gso)

    }

    fun out(){
        gsc.signOut()
            .addOnCompleteListener(this, OnCompleteListener{
                signout()
            })
    }

    private fun signout() {
        Toast.makeText(this@Create_account, "deconnect", Toast.LENGTH_SHORT)
    }

    private fun signIn() {
        var singinintent : Intent = gsc.signInIntent
        launcher.launch(singinintent)

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        result ->

        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {

        if (task.isSuccessful){

            val account : GoogleSignInAccount = task.result
            if (account != null){
                updateUI(account)
            }

        }
        else{

            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateUI(account: GoogleSignInAccount) {

        val credential =GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful){

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

}





