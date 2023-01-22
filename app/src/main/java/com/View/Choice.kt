package com.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityChoiceBinding
import com.example.wheeler.databinding.ActivityCreateAccountBinding
import com.example.wheeler.databinding.ActivityPhoneBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Choice : AppCompatActivity()
{
    lateinit var binding: ActivityChoiceBinding
    lateinit var storage: FirebaseStorage
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var database = FirebaseDatabase.getInstance()
        var img = intent.getStringExtra("img")

        auth = FirebaseAuth.getInstance()


        binding.yesWheel.setOnClickListener(){
            var intent = Intent(this, anychart::class.java)
            startActivity(intent)
        }

        Glide.with(baseContext).asBitmap().load(img).into(binding.imageView4)



    }}
