package com.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityCreateAccountBinding
import com.example.wheeler.databinding.ActivityPhoneNumberNewAccountBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PhoneNumberNewAccount : AppCompatActivity() {

    lateinit var binding: ActivityPhoneNumberNewAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")
        val mail = intent.getStringExtra("mail")
        val password = intent.getStringExtra("password")
        val birth = intent.getIntExtra("birth", 0)

        binding.Validate.setOnClickListener() {
            val phoneNumberStr = binding.PhoneNumb.text.toString()
            val phoneNumber = phoneNumberStr.toInt()

            val newUser = hashMapOf(
                "name" to "John Doe",
                "email" to mail,
                "birthdate" to birth,
                "password" to password,
                "phone" to phoneNumber
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
}


