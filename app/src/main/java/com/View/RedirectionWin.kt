package com.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityLoserScreenBinding
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.example.wheeler.databinding.ActivityWinnerScreenBinding
import com.google.firebase.database.DatabaseReference

class RedirectionWin: AppCompatActivity() {

    lateinit var binding: ActivityWinnerScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner_screen)
        binding = ActivityWinnerScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRedirection.setOnClickListener(){

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }}

