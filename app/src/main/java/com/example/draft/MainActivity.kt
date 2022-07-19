package com.example.draft

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
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
        val converter:  EditText = findViewById (R.id.gamblesum)
        val button = findViewById<Button>(R.id.button6)


        progressbar.max= 100;


        button.setOnClickListener()

        {

            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() < 200 ){
                with(progressbar) { progress= (converter.text.toString().toInt() * 100 / 200)




            }

        }
    }}}
