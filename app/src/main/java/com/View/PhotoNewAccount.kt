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
import java.util.*


class PhotoNewAccount : AppCompatActivity() {

    private lateinit var binding: ActivityPicNewAccountBinding
    private val REQUEST_IMAGE_PICK = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPicNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")
        val test = intent.getStringExtra("test") ?: "No found"
        val mail = intent.getStringExtra("mail") ?: "No email found"
        val password = intent.getStringExtra("password") ?: "No password found"
        val phone = intent.getStringExtra("phonenumb") ?: "No phone found"
        val birth = intent.getLongExtra("birth", 0)
        val birthDate = Date(birth)


        Log.d("debug", "Email: $mail")
        Log.d("debug", "Password: $password")
        Log.d("debug", "Birth: $birthDate")
        Log.d("debug", "test: $test")

        binding.addPic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        binding.Validate.setOnClickListener {
            val name = binding.Name.text.toString().trim()
            Log.d("debug", "Name: $name")

            val newUser = hashMapOf(
                "name" to name,
                "email" to mail,
                "birthdate" to birthDate,
                "password" to password, // Storing passwords in Firestore is not recommended for security reasons.
                "phone" to phone
            )

            usersCollection.add(newUser)
                .addOnSuccessListener { documentReference ->
                    Log.d("test", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("test", "Error adding document", e)
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            // Vous pouvez maintenant utiliser l'URI de l'image sélectionnée (par exemple, l'afficher dans un ImageView)
            onBackPressed()
        }
    }
}