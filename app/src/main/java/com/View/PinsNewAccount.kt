package com.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wheeler.databinding.ActivityPinsNewAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class PinsNewAccount : AppCompatActivity() {

    lateinit var binding: ActivityPinsNewAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            binding = ActivityPinsNewAccountBinding.inflate(layoutInflater)
            setContentView(binding.root)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")
        val test = intent.getStringExtra("test")
        val mail = intent.getStringExtra("mail")
        val password = intent.getStringExtra("password")
        val birth = intent.getLongExtra("birth", 0)
        val phone = intent.getStringExtra("phonenumb")
        val name = intent.getStringExtra("name")
        val birthDate = Date(birth)


        binding.Validate.setOnClickListener(){

            val newUser = hashMapOf(
                "name" to name,
                "email" to mail,
                "birthdate" to birthDate,
                "password" to password, // Storing passwords in Firestore is not recommended for security reasons.
                "phone" to phone,
                "Pin" to 1
            )

            usersCollection.add(newUser)
                .addOnSuccessListener { documentReference ->
                    Log.d("test", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("test", "Error adding document", e)
                }

            Log.d("debug", "Email: $mail")
            Log.d("debug", "Password: $password")
            Log.d("debug", "Birth: $birthDate")
            Log.d("debug", "test: $test")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
}}