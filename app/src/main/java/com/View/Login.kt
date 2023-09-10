package com.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ViewModel.LoginViewModel
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

 open class Login: AppCompatActivity() {

     private lateinit var viewModel: LoginViewModel
     lateinit var binding: ActivityLoginBinding // Déclaration de la variable pour le binding de l'interface utilisateur
     lateinit var gsc: GoogleSignInClient // Déclaration de la variable pour le client de connexion Google

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityLoginBinding.inflate(layoutInflater)
         setContentView(binding.root)


         val firebaseUser = FirebaseAuth.getInstance().currentUser // Récupération de l'utilisateur actuellement connecté à Firebase
         val uid = firebaseUser?.uid // Récupération de l'identifiant de l'utilisateur s'il est connecté
         var auth = FirebaseAuth.getInstance()

         // Création des options de connexion Google SignIn
         val gso: GoogleSignInOptions =
             GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                 .requestIdToken(getString(R.string.default_web_client_id)) // Demande d'un token d'identification
                 .requestEmail() // Demande d'accès à l'adresse e-mail de l'utilisateur
                 .build() // Construction des options

         // Initialisation du client de connexion Google SignIn en utilisant les options précédentes
         gsc = GoogleSignIn.getClient(this, gso)
         val signInIntent: Intent = gsc.signInIntent

         viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
         // Initialisez le ViewModel avec les dépendances nécessaires
         viewModel.initialize(gso, gsc)

         // Live data de la fonction handle
         viewModel.googleSignInResult.observe(this, Observer { googleSignInAccount ->
             if (googleSignInAccount != null) {
                 // La connexion Google est réussie, mettez à jour l'interface utilisateur
                 Log.d("test", "googlesigninaccount is null")
                 viewModel.updateUI(googleSignInAccount, auth)
             } else {
                 // La connexion Google a échoué, traitez l'erreur si nécessaire
                 Toast.makeText(this, "La connexion Google a échoué.", Toast.LENGTH_SHORT).show()
             }
         })

         viewModel.errorMessage.observe(this) { errorMessage ->
             if (!errorMessage.isNullOrEmpty()) {
                 Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
             }
         }

         // Lorsque le bouton "Mot de passe oublié" est cliqué
         binding.pwForgotten.setOnClickListener() {
             var intent = Intent(
                 this,
                 SendEmailToChangePassword::class.java
             ) // Créer une intention (intent) pour lancer l'activité SendEmailToChangePassword
             startActivity(intent) // Démarrer l'activité SendEmailToChangePassword
         }

         binding.buttonGmail.setOnClickListener() { // Lorsque le bouton "Gmail" est cliqué
             viewModel.signInWithGoogle(
                 gsc,
                 launcher
             ) // Appeler la fonction signIn() pour gérer la connexion via Google
         }

         binding.Login.setOnClickListener { // Lorsque le bouton "Connexion" est cliqué
             val email = binding.email.text.toString().trim()
             val pass = binding.pass.text.toString().trim()
             viewModel.setEmail(email)
             viewModel.setPassword(pass)
             viewModel.signInUser(auth, signInIntent)  // Appeler la fonction signInUser() pour gérer la connexion de l'utilisateur
         }

         // Observer pour le résultat de la connexion Google
         viewModel.signInSuccess.observe(this, Observer<Boolean> { success ->
             if (success) {
                 // La connexion Google est réussie, mettez à jour l'interface utilisateur
                 Log.d("test", "Google Sign-In successful")
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
                 finishAffinity()
                 Toast.makeText(
                     baseContext, "Authentication réussie.",
                     Toast.LENGTH_SHORT
                 ).show()
                 // Vous pouvez rediriger l'utilisateur ou effectuer d'autres actions ici
             } else {
                 // La connexion Google a échoué, traitez l'erreur si nécessaire
                 Log.d("test", "Google Sign-In failed, account est null")
                 val errorMessage = viewModel.errorMessage.value
                 if (!errorMessage.isNullOrEmpty()) {
                     Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                 }
                 // Afficher l'e-mail et le mot de passe ici
                 val email = viewModel.email.value
                 val password = viewModel.password.value
                 Log.d("test", "Email: $email, Password: $password")
             }
         })

         /* Observer pour le résultat de la connexion Google */
         viewModel.googleSignInResult.observe(this) { googleSignInAccount ->
             if (googleSignInAccount != null) {
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
                 finishAffinity()
                 Toast.makeText(
                     this, "Authentication réussie.",
                     Toast.LENGTH_SHORT
                 ).show()
             } else {
                 Log.d("test", "échec")
                 Toast.makeText(
                     this, "Authentication échouée.",
                     Toast.LENGTH_SHORT
                 ).show()
             }
         }
     }

     private val launcher =
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
             // Vérifier si le résultat est OK (utilisateur connecté avec succès)
             if (result.resultCode == Activity.RESULT_OK) {
                 // Récupérer les informations du compte Google connecté
                 val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                 // Traiter les résultats
                 viewModel.handleGoogleSignInResult(task)
             }
        }
    }