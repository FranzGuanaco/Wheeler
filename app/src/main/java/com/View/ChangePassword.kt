package com.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityChangePasswordBinding
import com.example.wheeler.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class ChangePassword : AppCompatActivity() {
    lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  <--->

        // Récupération de l'instance Firebase Auth
        val auth = FirebaseAuth.getInstance()

        binding.Registter.setOnClickListener(){

// Envoyer un e-mail de réinitialisation de mot de passe
        val emailAddress = "chevin.pierre.tomas@gmail.com"
        auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // L'e-mail a été envoyé avec succès
                    Toast.makeText(this, "Un e-mail de réinitialisation de mot de passe a été envoyé à $emailAddress", Toast.LENGTH_LONG).show()
                    println("reussi")
                } else {
                    // Erreur lors de l'envoi de l'e-mail
                    Toast.makeText(this, "Une erreur s'est produite lors de l'envoi de l'e-mail de réinitialisation de mot de passe", Toast.LENGTH_LONG).show()
                    println("faux")
                }
            }}}}