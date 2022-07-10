package com.example.draft

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_second)

    val facebook = findViewById<Button>(R.id.Facebook)

        facebook?.setOnClickListener {
            val intent = Intent(this,FirstFragment::class.java)
            startActivity(intent)
        }

    }}
