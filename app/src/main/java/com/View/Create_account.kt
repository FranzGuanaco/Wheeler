package com.View

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class Create_account: AppCompatActivity() {

    lateinit var binding: ActivityCreateAccountBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val db = Firebase.firestore

        binding.date.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this@Create_account,
                { _, year1, month1, dayOfMonth ->
                    binding.date.setText(String.format("%02d/%02d/%04d", dayOfMonth, (month1 + 1), year1))
                },
                year, month, day
            )
            datePickerDialog.show()
        }


        binding.create.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val birthdate = binding.date.text.toString().trim()
            val birthday = if (birthdate.isNotEmpty()) birthdate else ""

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("", "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)

                        val phoneNumberIntent = Intent(this, PhoneNumberNewAccount::class.java)
                        phoneNumberIntent.putExtra("mail", email)
                        phoneNumberIntent.putExtra("password", password)
                        phoneNumberIntent.putExtra("birth", birthday)

                        startActivity(phoneNumberIntent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = FirebaseAuth.getInstance().currentUser
                        val uid = firebaseUser?.uid
                        Log.d("test", "voici le test: $uid")
                        // Utilisez l'ID de l'utilisateur ici
                    } else {
                        Log.w("test", "raté", task.exception)
                        // La connexion a échoué
                    }
                }
        }


    }

    private fun updateUI(user: FirebaseUser?) {

        val intent = Intent(this, PhoneNumberNewAccount::class.java)
        startActivity(intent)

    }
}









