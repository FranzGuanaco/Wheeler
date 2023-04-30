package com.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityPicNewAccountBinding

class PhotoNewAccount : AppCompatActivity() {

    private lateinit var binding: ActivityPicNewAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPicNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
