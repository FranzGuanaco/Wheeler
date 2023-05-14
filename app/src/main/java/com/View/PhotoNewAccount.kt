package com.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityPicNewAccountBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.ktx.storage
import java.util.*


class PhotoNewAccount : AppCompatActivity() {

    private lateinit var binding: ActivityPicNewAccountBinding
    private val REQUEST_IMAGE_PICK = 1
    val db = Firebase.firestore
    val storage =  Firebase.storage
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPicNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val test = intent.getStringExtra("test")
        val mail = intent.getStringExtra("mail")
        val password = intent.getStringExtra("password")
        val birth = intent.getLongExtra("birth", 0)
        val phone = intent.getStringExtra("phonenumb")

        Log.d("voici le resultat", "Email: $mail")
        Log.d("voici le resultat", "Password: $password")
        Log.d("voici le resultat", "Birth: $birth")
        Log.d("voici le resultat", "test: $test")

        binding.addPic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        binding.Validate.setOnClickListener {
            val name = binding.Name.text.toString().trim()
            Log.d("debug", "Name: $name")

            val intent = Intent(this, PinsNewAccount::class.java)
            intent.putExtra("name", name)
                .putExtra("phonenumb", intent.getStringExtra("phonenumb"))

            // Ajouter les valeurs reçues de la Classe 1 ici
            intent.putExtra("test", test)
            intent.putExtra("mail", mail)
            intent.putExtra("password", password)
            intent.putExtra("birth", birth)
            intent.putExtra("phonenumb", phone)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                // Utilisez l'URI de l'image sélectionnée pour afficher l'image ou effectuer d'autres opérations
                displaySelectedImage(selectedImageUri)
            }
        }}

        private fun displaySelectedImage(selectedImageUri: Uri) {
            // Affichez l'image sélectionnée dans un ImageView
            binding.userPic.setImageURI(selectedImageUri)
            uploadImageToFirebase(selectedImageUri)
        }

    private fun uploadImageToFirebase(selectedImageUri: Uri) {
        val user = auth.currentUser
        if (user != null) {
            val storageRef = storage.reference
            val imageRef = storageRef.child("AvatarPicture/${user.uid}.jpg")

            val uploadTask = imageRef.putFile(selectedImageUri)
            uploadTask.addOnSuccessListener {
                // Image téléchargée avec succès
                imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    // Utilisez l'URL de téléchargement pour mettre à jour les données utilisateur dans Firestore ou Firebase Authentication
                    updateUserProfile(downloadUri)

                    //Obtenir la référence à la base de données
                    val database = FirebaseDatabase.getInstance()

                    //Obtenir la référence à 'Avatar'/'uid'
                    val avatarRef = database.getReference("Avatar/${user.uid}")

                    //Mettre à jour la valeur
                    avatarRef.setValue(downloadUri.toString())
                        .addOnSuccessListener {
                            //Écouteur pour le succès de l'opération de mise à jour
                            Log.d("upload", "Image URL successfully written to database")
                        }
                        .addOnFailureListener { exception ->
                            //Écouteur pour les échecs de l'opération de mise à jour
                            Log.e("upload", "Failed to write image URL to database", exception)
                        }
                }
            }.addOnFailureListener { exception ->
                // Gestion des erreurs
                Log.e("upload", "Image upload failed: ", exception)
            }
        } else {
            Log.e("upload", "User not logged in")
        }
    }

    private fun updateUserProfile(imageUrl: Uri) {
        // Mise à jour du profil utilisateur dans Firebase Authentication
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setPhotoUri(imageUrl)
            .build()
        user?.updateProfile(profileUpdates)
            ?.addOnSuccessListener {
                Log.d("update", "User profile updated.")
            }
            ?.addOnFailureListener { exception ->
                Log.e("update", "User profile update failed: ", exception)
            }

        // Mise à jour des données utilisateur dans Firestore
        val db = FirebaseFirestore.getInstance()
        val userDocRef = db.collection("users").document(user!!.uid)
        userDocRef.update("photoUrl", imageUrl.toString())
            .addOnSuccessListener {
                Log.d("update", "User document updated.")
            }
            .addOnFailureListener { exception ->
                Log.e("update", "User document update failed: ", exception)
            }
    }

}
