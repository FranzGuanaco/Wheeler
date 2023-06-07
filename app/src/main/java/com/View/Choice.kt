package com.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityChoiceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
open class Choice : AppCompatActivity()
{
    lateinit var binding: ActivityChoiceBinding
    lateinit var storage: FirebaseStorage
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var img = intent.getStringExtra("img")
        var data = intent.getStringExtra("figure")
        val db = Firebase.firestore
        val usersCollection = db.collection("Session")


            Glide.with(baseContext).asBitmap().load(img).into(binding.imageView4)

            binding.Price.text = "Price: ${data} $"


            auth = FirebaseAuth.getInstance()

           // var img = intent.getStringExtra("img")

            binding.yesWheel.setOnClickListener() {

                val newGame = hashMapOf(
                    "State" to "test"
                )
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    val userId = user.uid
                    usersCollection.document(userId)
                        .set(newGame) // storage activé
                        .addOnSuccessListener {
                            Log.d("test", "DocumentSnapshot added with ID: $userId")
                        }
                        .addOnFailureListener { e ->
                            Log.w("test", "Error adding document", e)
                        }
                } else {
                    Log.e("test", "User not logged in")
                }

                var intent = Intent(this, anychart::class.java)
                startActivity(intent)
            }

            binding.noWheel.setOnClickListener() {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }
        }
    }