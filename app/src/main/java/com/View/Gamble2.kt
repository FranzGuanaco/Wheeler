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
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlin.random.Random


open class Gamble2 : anychart() {

    private lateinit var binding: ActivityAnychartBinding

    private val random = Random.nextInt(1, 360)
    private val randomDuration = Random.nextInt(1800, 6000)
    private val randomToFloat = random.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        binding = ActivityAnychartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val play = findViewById<Button>(R.id.play)
        val anim: AnimatedPieView = findViewById(R.id.pieView)
        val config: AnimatedPieViewConfig = AnimatedPieViewConfig()
        val name2 = intent.getDoubleExtra("valeurReturn", 1.1)
        var price = intent.getStringExtra("price")


        config.addData(SimplePieInfo((2000.0 - name2), Color.parseColor("#ADB7AE")))
        config.addData(SimplePieInfo(name2, Color.parseColor("#FCE300"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()

        binding.Gamblename.text = price
        Log.d("Price", "Price value: $price")

        fun refresh2(valeur: Double) {
            val intent = Intent(this, Gamble::class.java)
            intent.putExtra("valeur", valeur)
            startActivity(intent)
        }

        button.setOnClickListener {
            refresh2(valeur = 1000.0)
        }

        button2.setOnClickListener {
            refresh2(valeur = 100.0)
        }

        play.setOnClickListener {
            val turned = listOf(720, 1080, 1440)
            val randomturn = turned.random()
            val turn = randomToFloat + randomturn

            val animations = ObjectAnimator.ofFloat(anim, "rotation", turn).apply {
                duration = randomDuration.toLong()
            }

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()

            if (name2 == 100.0) {
                if (price != null) {
                    win(price)
                }
            } else {
                if (price != null) {
                    win2(price)
                }
            }
        }
    }

    private fun win(price: String) {
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
                    "Players" to "test",
                    "State" to "Reusssi"
                )
                val userId = user.uid
                gameCollection.document(userId)
                    .set(newGame)
                    .addOnSuccessListener {
                        Log.d("test", "Game document added with ID: test")
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

            if (user != null) {
                val newGame = hashMapOf(
                    "Prix" to price,
                    "Players" to "test",
                    "State" to "Reusssi"
                )
                val userId = user.uid
                gameCollection.document(userId)
                    .set(newGame)
                    .addOnSuccessListener {
                        Log.d("test", "Game document added with ID: test")
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

    private fun win2(price:String) {
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
                    "Players" to "test",
                    "State" to "Reusssi"
                )
                val userId = user.uid
                gameCollection.document(userId)
                    .set(newGame)
                    .addOnSuccessListener {
                        Log.d("test", "Game document added with ID: test")
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

            if (user != null) {
            val newGame = hashMapOf(
                "Prix" to price,
                "Players" to "test",
                "State" to "Reusssi"
            )
                val userId = user.uid
            gameCollection.document(userId)
                .set(newGame)
                .addOnSuccessListener {
                    Log.d("test", "Game document added with ID: test")
                }
                .addOnFailureListener { e ->
                    Log.w("test", "Error adding game document", e)
                }

            Handler().postDelayed({
                val intent = Intent(this, RedirectionLose::class.java)
                startActivity(intent)
            }, 6000)
        }
    }}
}

