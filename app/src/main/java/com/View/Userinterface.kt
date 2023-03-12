package com.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityUserinterfaceBinding

class Userinterface : AppCompatActivity() {
    lateinit var binding: ActivityUserinterfaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userinterface)
        binding = ActivityUserinterfaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.account.setOnClickListener(){

            var intent = Intent(this, Disconnect::class.java)
            startActivity(intent)
        }

    }
}