package com.View

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ViewModel.EmailCheckViewModel
import com.example.wheeler.databinding.ActivityEmailCheckBinding
import com.google.firebase.auth.FirebaseAuth

class EmailCheck : AppCompatActivity() {

    private lateinit var binding: ActivityEmailCheckBinding
    private lateinit var viewModel: EmailCheckViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var part1EditText: EditText
    private lateinit var part2EditText: EditText
    private lateinit var part3EditText: EditText
    private lateinit var part4EditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codeVerification = intent.getStringExtra("codeVerification")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val birthdate = intent.getStringExtra("birthdate")

        auth = FirebaseAuth.getInstance()
        viewModel = EmailCheckViewModel()

        part1EditText = binding.part1
        part2EditText = binding.part2
        part3EditText = binding.part3
        part4EditText = binding.part4

        setupEditTextListeners()

        binding.Submit.setOnClickListener {
            val part1Text = part1EditText.text.toString()
            val part2Text = part2EditText.text.toString()
            val part3Text = part3EditText.text.toString()
            val part4Text = part4EditText.text.toString()

            val enteredCode: String = "$part1Text$part2Text$part3Text$part4Text"

            if (enteredCode == codeVerification) {
                Log.d("code à entrer", "$enteredCode")
                viewModel.createAccount(email!!, password!!, birthdate!!)
            } else {
                Log.d("code à entrer", "$enteredCode")
                Log.e("Erreur de mot de passe", "Mot de passe incorrect")

                val errorMessage = "Mot de passe incorrect"
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.navigationListener = object : EmailCheckViewModel.NavigationListener {
            override fun onVerificationSuccess() {
                val photoIntent = Intent(this@EmailCheck, PhotoNewAccount::class.java)
                startActivity(photoIntent)
            }
        }
    }

    private fun setupEditTextListeners() {
        val editTexts = arrayOf(part1EditText, part2EditText, part3EditText, part4EditText)

        for (i in 0 until editTexts.size - 1) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        editTexts[i + 1].requestFocus()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
}
