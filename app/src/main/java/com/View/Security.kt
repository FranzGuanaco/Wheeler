package com.View

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.example.wheeler.databinding.ActivitySecurityBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


class Security : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: DatabaseReference
    lateinit var binding: ActivitySecurityBinding
    lateinit var image: String
    lateinit var gsc: GoogleSignInClient
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)
        binding = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // parametre Firebase pour se connecter via l'API Google
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        gsc = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser

// bouton de deconnexion
        binding.Logout.setOnClickListener(){
            auth.signOut()
            var intent = Intent(this, Login::class.java)
            startActivity(intent)

        }

        // bouton de suppression de compte
        binding.Deleteaccount.setOnClickListener {

            val currentUser = FirebaseAuth.getInstance().currentUser
            currentUser?.delete()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "User account deleted.")

                    // Marquez l'utilisateur comme "supprimé" dans la base de données
                    val db = FirebaseFirestore.getInstance()
                    val docRef = db.collection("users").document(currentUser.uid)

                    docRef.update("status", "deleted")
                        .addOnSuccessListener {
                            Log.d("TAG", "User marked as deleted.")
                        }
                        .addOnFailureListener { e ->
                            Log.w("TAG", "Error updating document", e)
                        }

                    // Redirigez l'utilisateur vers la page de connexion ou effectuez d'autres actions appropriées ici.
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
                else {
                    // Gestion des erreurs
                    Log.d("TAG", "Failed to delete user account.")
                }
            }

        }
    }}