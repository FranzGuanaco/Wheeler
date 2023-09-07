package com.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wheeler.databinding.ActivitySendEmailToChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random


class SendEmailToChangePassword : AppCompatActivity() {
    lateinit var binding: ActivitySendEmailToChangePasswordBinding
    private lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendEmailToChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.Next.setOnClickListener {
            val email = binding.mail.text.toString()
            val code = Random.nextInt(1000, 9999)
            val newCode = code.toString()

            auth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val signInMethods = task.result?.signInMethods
                        if (signInMethods?.isEmpty() != false) {
                            // E-mail non associé à un compte, affichez une erreur
                            Toast.makeText(
                                this,
                                "Il n'y a aucun compte associé à cet e-mail.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // L'e-mail est associé à un compte, continuez la procédure
                            sendPasswordResetEmail(email, newCode)
                        }
                    } else {
                        // Erreur lors de la récupération des méthodes de connexion, affichez une erreur
                        Toast.makeText(
                            this,
                            "Erreur lors de la vérification de l'e-mail.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun sendPasswordResetEmail(email: String, newCode: String) {
        // Envoi de l'e-mail avec le code de vérification
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Récupération de l'utilisateur actuel
                    val user = auth.currentUser

                    // Mise à jour du mot de passe de l'utilisateur avec le code de vérification
                    user?.updatePassword(newCode)
                        ?.addOnCompleteListener { passwordUpdateTask ->
                            if (passwordUpdateTask.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Un e-mail de réinitialisation du mot de passe a été envoyé.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Redirection vers l'activité ChangePassword après l'envoi réussi de l'e-mail
                                val intent = Intent(this, ChangePassword::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this,
                                    "Erreur lors de la réinitialisation du mot de passe.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this,
                        "Erreur lors de l'envoi de l'e-mail de réinitialisation du mot de passe.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}


