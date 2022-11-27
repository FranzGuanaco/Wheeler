package com.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.draft.R

class Login: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.classiclogin)

        var password = findViewById<EditText>(R.id.Password)
        var name = findViewById<EditText>(R.id.name)
        var validation = findViewById<Button>(R.id.validationBt)
        var load = findViewById<Button>(R.id.load)

        val sharedPref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        var editor = sharedPref.edit()

       fun test(){
            val user = name.text.toString()
            val pass = password.text.toString()

            editor.apply {
                putString("name", user)
                putString("code", pass)
                apply()
            }}

        load.setOnClickListener(){
            val user = sharedPref.getString("name", null)
            val pass = sharedPref.getString("code", null)

            name.setText(user)
            password.setText(pass)

        }
        }
}