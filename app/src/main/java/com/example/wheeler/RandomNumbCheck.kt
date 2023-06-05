package com.example.wheeler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wheeler.databinding.ActivityChangePasswordBinding
import com.example.wheeler.databinding.ActivityRandomNumbCheckBinding

class RandomNumbCheck : AppCompatActivity() {
    lateinit var binding: ActivityRandomNumbCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_numb_check)

        binding = ActivityRandomNumbCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var result = binding.code.text.toString()
        val code = intent.getStringExtra("code")

        binding.Next.setOnClickListener(){

        if (result == code){
            Log.d("check de code","Nous avon reussi la connexion car le code est celui envoyé par mail")
        }
        else{
            Log.i("check de code:","Nous avons échoué le code n'est pas le bon")
        }
    }
}}