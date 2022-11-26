package com.order

import android.content.Intent
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
        var name = findViewById<TextView>(R.id.textView4)
        var validation = findViewById<Button>(R.id.validationBt)

        validation.setOnClickListener(){

            var user = name
            var entered = password.text.toString()
            DisplayAndSave(entered, user);

        }}

    private fun DisplayAndSave(entered: String, user: TextView) {

        user.setText(entered);

    }
}