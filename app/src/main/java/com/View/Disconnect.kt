package com.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityChoiceBinding
import com.example.wheeler.databinding.ActivityDisconnectBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Disconnect : AppCompatActivity() {
    lateinit var binding: ActivityDisconnectBinding
    lateinit var auth: FirebaseAuth
    lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prize)
        binding = ActivityDisconnectBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        gsc = GoogleSignIn.getClient(this, gso)

        var test = Login()
        //  test.auth = FirebaseAuth.getInstance()

        binding.button5.setOnClickListener(){
            test.out()
            auth.signOut()
            var intent = Intent(this, Login::class.java)
            startActivity(intent)

        }

    }

}