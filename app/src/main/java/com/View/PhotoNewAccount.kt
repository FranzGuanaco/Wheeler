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
            // Vous pouvez maintenant utiliser l'URI de l'image sélectionnée (par exemple, l'afficher dans un ImageView)
            onBackPressed()
        }
    }
}