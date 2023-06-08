package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlin.random.Random


open class Gamble : anychart() {

    private lateinit var binding: ActivityAnychartBinding
    val user = FirebaseAuth.getInstance().currentUser
    val db = Firebase.firestore
    var random = Random.nextInt(1, 360)
    var randomDuration = Random.nextInt(1800, 6000)
    var randomToFloat = random.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        binding = ActivityAnychartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val name = intent.getDoubleExtra("valeur", 1.1)
        var price = intent.getStringExtra("price")
        var gamename = intent.getStringExtra("GameName")

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val play = findViewById<Button>(R.id.play)
        val anim: AnimatedPieView = findViewById(R.id.pieView)
        val config: AnimatedPieViewConfig = AnimatedPieViewConfig()


        config.addData(SimplePieInfo((2000.0 - name), Color.parseColor("#ADB7AE")))
        config.addData(SimplePieInfo(name, Color.parseColor("#FCE300"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()


        fun refresh(valeurReturn: Double) {
            val intent = Intent(this, Gamble2::class.java)
            intent.putExtra("valeurReturn", valeurReturn)
            startActivity(intent)
        }


        button.setOnClickListener() {
            refresh(valeurReturn = 1000.0)
        }


        button2.setOnClickListener() {
            refresh(valeurReturn = 100.0)
        }


        play.setOnClickListener() {

            var turned = listOf(720, 1080, 1440)
            var randomturn = turned.random()
            var turn = randomToFloat + randomturn

            val animations =
                ObjectAnimator.ofFloat(anim, "rotation", turn).apply {
                    duration = randomDuration.toLong()
                }

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()

            if (name == 100.0) {
                win()
            } else {
                win2()
            }
        }
    }

    private fun win() {
        if (random in 360..370 || random in 1..12) {
            println("gagné")
            val newUser = hashMapOf(
                "Pin" to "100"
            )

            val user = FirebaseAuth.getInstance().currentUser
            val db = Firebase.firestore
            val usersCollection = db.collection("users")
            val gameCollection = db.collection("Session")

            if (user != null) {
                val userId = user.uid

                usersCollection.document(userId)
                    .set(newUser)
                    .addOnSuccessListener {
                        Log.d("test", "User document added with ID: $userId")
                    }
                    .addOnFailureListener { e ->
                        Log.w("test", "Error adding user document", e)
                    }

                val newGame = hashMapOf(
                    "Prix" to price,
                    "Players" to user.uid,
                    "State" to "Pending"
                )

                gameCollection.document(this.gamename)
                    .set(newGame)
                    .addOnSuccessListener {
                        Log.d("test", "Game document added with ID: $gamename")
                    }
                    .addOnFailureListener { e ->
                        Log.w("test", "Error adding game document", e)
                    }

                Handler().postDelayed({
                    val intent = Intent(this, RedirectionWin::class.java)
                    startActivity(intent)
                }, 6000)
            }
        } else {
            println("perdu")
            val newUser = hashMapOf("Pin" to "0")

            val user = FirebaseAuth.getInstance().currentUser
            val db = Firebase.firestore
            val usersCollection = db.collection("users")
            val gameCollection = db.collection("Session")

            if (user != null) {
                val userId = user.uid

                usersCollection.document(userId)
                    .set(newUser)
                    .addOnSuccessListener {
                        Log.d("test", "User document added with ID: $userId")
                    }
                    .addOnFailureListener { e ->
                        Log.w("test", "Error adding user document", e)
                    }

                Handler().postDelayed({
                    val intent = Intent(this, RedirectionLose::class.java)
                    startActivity(intent)
                }, 6000)
            }
        }
    }



    private fun win2() {

        if (random in 1..180) {

            println("gagné")

            Handler().postDelayed({ val intent = Intent(this, RedirectionWin::class.java)
                startActivity(intent)
            }, 6000)

        } else {
            println("perdu")

            Handler().postDelayed({
                val intent = Intent(this, RedirectionLose::class.java)
                startActivity(intent)
            }, 6000)

        }
    }
}