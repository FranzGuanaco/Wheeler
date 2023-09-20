package com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class EmailCheckViewModel: ViewModel() {

    private val _accountcreated = MutableLiveData<String>()
    val accountcreated: LiveData<String> get() = _accountcreated

    // Créez une interface pour gérer les événements de navigation
    interface NavigationListener {
        fun onVerificationSuccess()
    }

    // Propriété pour écouter les événements de navigation
    var navigationListener: NavigationListener? = null

    fun createAccount(email: String, password: String, birthdate: String) {
        // Créez le compte utilisateur avec email et mot de passe
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user

                    // Envoi d'un email de vérification à l'utilisateur
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                val message = "Verification email sent to ${user.email}"
                                _accountcreated.postValue(message)

                                // Signalez le succès de la vérification à la vue
                                navigationListener?.onVerificationSuccess()
                            } else {
                                val errorMessage = "Failed to send verification email."
                                _accountcreated.postValue(errorMessage)
                            }
                        }
                } else {
                    val errorMessage = "Failed to create user: ${task.exception?.message}"
                    _accountcreated.postValue(errorMessage)
                }
            }
    }
}