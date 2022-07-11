package com.example.draft

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ProgressBar


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_gamble)
/*
    val facebook = findViewById<Button>(R.id.Facebook)

        facebook?.setOnClickListener {
            val intent = Intent(this,FirstFragment::class.java)
            startActivity(intent)
        }
*/
        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        progressbar.visibility= View.VISIBLE
        val button = findViewById<Button>(R.id.button5)

        button.setOnClickListener{
            progressbar.incrementProgressBy(10)

        }


    }}
