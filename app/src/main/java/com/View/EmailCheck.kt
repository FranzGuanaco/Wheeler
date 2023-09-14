package com.View

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.databinding.ActivityEmailCheckBinding
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth


class EmailCheck: AppCompatActivity() {

    private lateinit var binding: ActivityEmailCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("mail")

        Log.d("check de la transmission du mail", "$email")


    }}