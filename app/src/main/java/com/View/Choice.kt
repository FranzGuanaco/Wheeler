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
    val user = FirebaseAuth.getInstance().currentUser
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var img = intent.getStringExtra("img")
        var data = intent.getStringExtra("figure")
        var price = intent.getStringExtra("price")
        var gamename = intent.getStringExtra("GameName")

            Glide.with(baseContext).asBitmap().load(img).into(binding.imageView4)

            binding.Price.text = "Price: ${data} $"
        binding.texttest.text = price

        binding.yesWheel.setOnClickListener() {
            val intent = Intent(this, anychart::class.java)
            intent.putExtra("price", price)
            intent.putExtra("GameName", gamename)
            startActivity(intent)
        }


        binding.noWheel.setOnClickListener() {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }