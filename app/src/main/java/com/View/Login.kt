package com.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.SendEmailToChangePassword
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

     lateinit var binding: ActivityLoginBinding // Déclaration de la variable pour le binding de l'interface utilisateur
     lateinit var gsc: GoogleSignInClient // Déclaration de la variable pour le client de connexion Google
     lateinit var auth: FirebaseAuth // Déclaration de la variable pour l'instance de FirebaseAuth
     val firebaseUser = FirebaseAuth.getInstance().currentUser // Récupération de l'utilisateur actuellement connecté à Firebase
     val uid = firebaseUser?.uid // Récupération de l'identifiant de l'utilisateur s'il est connecté

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         binding = ActivityLoginBinding.inflate(layoutInflater)
         setContentView(binding.root)

         auth = FirebaseAuth.getInstance()

         // Création des options de connexion Google SignIn
         val gso: GoogleSignInOptions =
             GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                 .requestIdToken(getString(R.string.default_web_client_id)) // Demande d'un token d'identification
                 .requestEmail() // Demande d'accès à l'adresse e-mail de l'utilisateur
                 .build() // Construction des options

         // Initialisation du client de connexion Google SignIn en utilisant les options précédentes
         gsc = GoogleSignIn.getClient(this, gso)
         val firebaseUser =
             FirebaseAuth.getInstance().currentUser // Récupération de l'utilisateur actuellement connecté à Firebase Authentication
         val uid =
             firebaseUser?.uid // Récupération de l'identifiant unique (UID) de l'utilisateur, s'il est connecté


         // Lorsque le bouton "Mot de passe oublié" est cliqué
         binding.pwForgotten.setOnClickListener() {
             var intent = Intent(this, SendEmailToChangePassword::class.java) // Créer une intention (intent) pour lancer l'activité SendEmailToChangePassword
             startActivity(intent) // Démarrer l'activité SendEmailToChangePassword
         }

         binding.buttonGmail.setOnClickListener() { // Lorsque le bouton "Gmail" est cliqué
             signIn() // Appeler la fonction signIn() pour gérer la connexion via Google
         }

         binding.Login.setOnClickListener { // Lorsque le bouton "Connexion" est cliqué
             signInUser()  // Appeler la fonction signInUser() pour gérer la connexion de l'utilisateur
         }
     }


     private fun signIn() {  // Fonction pour lancer le processus de connexion via Google
         var signInIntent: Intent = gsc.signInIntent  // Créer une intention (intent) pour lancer l'écran de connexion Google
         launcher.launch(signInIntent) // Lancer l'activité de connexion Google et attendre le résultat
     }

     // Mise en place d'un gestionnaire d'activité pour recevoir les résultats de l'écran de connexion Google
     private val launcher =
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
             // Vérifier si le résultat est OK (utilisateur connecté avec succès)
             if (result.resultCode == Activity.RESULT_OK) {
                 // Récupérer les informations du compte Google connecté
                 val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                 // Traiter les résultats
                 handleResults(task)
             }
         }

     // Fonction pour traiter les résultats de la connexion Google
     private fun handleResults(task: Task<GoogleSignInAccount>) {
         if (task.isSuccessful) {
             // Récupérer le compte Google connecté avec succès
             val account: GoogleSignInAccount = task.result
             if (account != null) {
                 // Mettre à jour l'interface utilisateur pour refléter la connexion réussie
                 updateUI(account)
             }
         } else {
             // Afficher un message d'erreur en cas d'échec de la connexion Google
             Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
         }
     }

     // Fonction pour mettre à jour l'interface utilisateur après une connexion réussie via Google
     private fun updateUI(account: GoogleSignInAccount) {
         // Obtenir les informations d'identification de Google pour Firebase
         val credential = GoogleAuthProvider.getCredential(account.idToken, null)

         // Authentifier l'utilisateur via Firebase avec les informations d'identification
         auth.signInWithCredential(credential).addOnCompleteListener { task ->
             if (task.isSuccessful) {
                 // La connexion avec les informations d'identification est réussie
                 // Rediriger l'utilisateur vers l'écran principal (MainActivity)
                 val intent = Intent(this, MainActivity::class.java)
                 startActivity(intent)
             } else {
                 // Afficher un message d'erreur si la connexion avec les informations d'identification échoue
                 Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
             }
         }
     }



     private fun signInUser() {
         var email = binding.email.text.toString().trim()
         var pass = binding.pass.text.toString().trim()

         if (email.isNotEmpty()) {
             auth.signInWithEmailAndPassword(email, pass)
                 .addOnCompleteListener { signIn ->
                     if (signIn.isSuccessful) {
                         val intent = Intent(this, MainActivity::class.java)
                         startActivity(intent)
                          finishAffinity()
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





