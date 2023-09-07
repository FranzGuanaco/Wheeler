package com.ViewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.View.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginViewModel : ViewModel() {
    private lateinit var gsc: GoogleSignInClient
    private val _googleSignInResult = MutableLiveData<GoogleSignInAccount?>()
    val googleSignInResult: LiveData<GoogleSignInAccount?> = _googleSignInResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _signInSuccess = MutableLiveData<Boolean>()
    val signInSuccess: LiveData<Boolean> get() = _signInSuccess


    fun setEmail(email: String) {
        _email.value = email.toString().trim()
    }

    fun setPassword(password: String) {
        _password.value = password.toString().trim()
    }

    fun initialize(gso: GoogleSignInOptions, googleSignInClient: GoogleSignInClient) {
        gsc = googleSignInClient
        // Initialisez ici d'autres propriétés ViewModel si nécessaire
    }

    fun signInWithGoogle(gsc: GoogleSignInClient, launcher: ActivityResultLauncher<Intent>) {
        val signInIntent: Intent = gsc.signInIntent
        launcher.launch(signInIntent)
    }

    fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            // Récupérer le compte Google connecté avec succès
            val account: GoogleSignInAccount = task.result
            _googleSignInResult.postValue(account) // Mettez à jour la LiveData avec le compte Google réussi
        } else {
            _googleSignInResult.postValue(null) // La connexion Google a échoué, mettez à jour la LiveData avec null
        }
    }

    fun updateUI(account: GoogleSignInAccount, auth: FirebaseAuth) {
        // Obtenir les informations d'identification de Google pour Firebase
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        // Authentifier l'utilisateur via Firebase avec les informations d'identification
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _googleSignInResult.postValue(account)
            } else {
                _googleSignInResult.postValue(null)
                _errorMessage.postValue(task.exception?.message ?: "Une erreur s'est produite.")
            }
        }
    }

    fun signInUser(auth: FirebaseAuth, result: Intent) {
        val email = _email.value
        val password = _password.value

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                            // Obtenir le compte Google connecté avec succès
                        _signInSuccess.postValue(true)
                        } else {
                        // La connexion avec les informations du compte Google a échoué
                        _googleSignInResult.postValue(null)
                        _errorMessage.postValue("Erreur lors de la connexion avec le compte Google")
                    }}
        } else {
            // Gérer le cas où 'email' est vide ici si nécessaire
            _googleSignInResult.postValue(null)
            _errorMessage.postValue("L'adresse e-mail ou le mot de passe est vide")
        }
    }
}









