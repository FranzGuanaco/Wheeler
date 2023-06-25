package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlin.random.Random


open class Gamble : anychart() {

    private lateinit var binding: ActivityAnychartBinding
    var random = Random.nextInt(1, 360)
    var randomDuration = Random.nextInt(1800, 6000)
    var randomToFloat = random.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        binding = ActivityAnychartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getDoubleExtra("valeur", 1.1)
        var price = intent.getStringExtra("price")
        var gamename = intent.getStringExtra("GameName")
        binding.Gamblename.text = "Gamble"
        Log.d("Price", "Price value: $price")

// ANimation
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
            intent.putExtra("price", price)
            intent.putExtra("GameName", gamename)

            startActivity(intent)
        }

// changer d'activité et faire passer la valeur de la mise dans l'autre classe
        binding.button.setOnClickListener() {
            refresh(valeurReturn = 1000.0)
        }

// changer d'activité et faire passer la valeur de la mise dans l'autre classe
        binding.button2.setOnClickListener() {
            refresh(valeurReturn = 100.0)
        }

//lancer le jeu
        binding.play.setOnClickListener() {

            var turned = listOf(720, 1080, 1440)
            var randomturn = turned.random()
            var turn = randomToFloat + randomturn

            val animations = ObjectAnimator.ofFloat(anim, "rotation", turn).apply {
                    duration = randomDuration.toLong()
                }

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()

// deux option selon la mise pour calibrer l'arc de cercle
            if (name == 100.0) {
                if (price != null) {
                    if (gamename != null) {
                        win(price, gamename)
                    }
                }
            } else {
                win2(price, gamename)
            }
        }
    }

    private fun win(price: String, gamename: String) {

        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore
        // collection du joueur
        val usersCollection = db.collection("users")
        // collection du jeu
        val gameCollection = db.collection("Session")

        if (random in 360..370 || random in 1..12) {
            println("gagné")

            if (user != null) {
                val newGame = hashMapOf(
                    "Prix" to price,
                    "Players" to user.uid,
                    "State" to "Fini",
                    "Winner" to user.uid
                )

                gameCollection.document(gamename)
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
            val user = FirebaseAuth.getInstance().currentUser
            val db = Firebase.firestore
            // collection du joueur
            val usersCollection = db.collection("users")
            // collection du jeu
            val gameCollection = db.collection("Session")

            println("perdu")

            if (user != null) {
                val newGame = hashMapOf(
                    "Prix" to price,
                    "Players" to user.uid,
                    "State" to "Fini",
                    "Winner" to "not you"
                )

                gameCollection.document(gamename)
                    .set(newGame)
                    .addOnSuccessListener {
                        Log.d("test", "Game document added with ID: $gamename")
                    }
                    .addOnFailureListener { e ->
                        Log.w("test", "Error adding game document", e)
                    }

                Handler().postDelayed({
                    val intent = Intent(this, RedirectionLose::class.java)
                    startActivity(intent)
                }, 6000)
            }
        }
    }



    private fun win2(price: String?, gamename: String?) {

        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore
        // collection du joueur
        val usersCollection = db.collection("users")
        // collection du jeu
        val gameCollection = db.collection("Session")

        if (random in 1..180) {

            println("gagné")

            if (user != null) {
                val newGame = hashMapOf(
                    "Prix" to price,
                    "Players" to user.uid,
                    "State" to "Fini",
                    "Winner" to user.uid
                )

                gameCollection.document(gamename!!)
                    .set(newGame)
                    .addOnSuccessListener {
                        Log.d("test", "Game document added with ID: $gamename")
                    }
                    .addOnFailureListener { e ->
                        Log.w("test", "Error adding game document", e)
                    }

                Handler().postDelayed({ val intent = Intent(this, RedirectionWin::class.java)
                startActivity(intent)
            }, 6000)

        } else {

                val user = FirebaseAuth.getInstance().currentUser
                val db = Firebase.firestore
                // collection du joueur
                val usersCollection = db.collection("users")
                // collection du jeu
                val gameCollection = db.collection("Session")

            println("perdu")

                if (user != null) {
                    val newGame = hashMapOf(
                        "Prix" to price,
                        "Players" to user.uid,
                        "State" to "Fini",
                        "Winner" to "not you"
                    )

                    gameCollection.document(gamename!!)
                        .set(newGame)
                        .addOnSuccessListener {
                            Log.d("test", "Game document added with ID: $gamename")
                        }
                        .addOnFailureListener { e ->
                            Log.w("test", "Error adding game document", e)
                        }


                    Handler().postDelayed({
                val intent = Intent(this, RedirectionLose::class.java)
                startActivity(intent)
            }, 6000)

        }
    }
}}}