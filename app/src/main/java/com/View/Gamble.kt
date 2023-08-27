package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.TextView
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlin.random.Random


open class Gamble : anychart() {

    private lateinit var binding: ActivityAnychartBinding // fragment utilisé pour la classe
    private lateinit var countdownTimer: CountDownTimer // compteur pour declencher la roue
    var random = Random.nextInt(1, 360) // valeur aleatoire pour la puissance des tours de la roue
    var randomDuration = Random.nextInt(1800, 6000) // valeur aleatoire pour la durée des tours
    var randomToFloat = random.toFloat() // conversion de la puissance des tours en float

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        binding = ActivityAnychartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getDoubleExtra("valeur", 1.1) // récuperation de la valeur de la classe predecente (valeur du pari)
        val price = intent.getStringExtra("price") // récuperation de la valeur de la classe predecente (valeur du prix du lot)
        val gamename = intent.getStringExtra("GameName") // récuperation de la valeur de la classe predecente (nom du lot)
        val initialCountdown: Long = 60000 // 1 minute en millisecondes
        val countdownInterval: Long = 1000 // 1 seconde en millisecondes
        val database = FirebaseDatabase.getInstance()
        val gameRef = database.getReference("Game")
        // couleur pour identifier les paris
        val colors = arrayOf(
            "#E50A0A",
            "#E5A90A",
            "#E9F719",
            "#56F719",
            "#19F7A3",
            "#19A6F7",
            "#061F7F",
            "#5F17DC",
            "#B511F7",
            "#5C1414"
        )
        var index = 0


        binding.Gamblename.text = "Gamble" // reference pour savoir dans quelle classe on se trouve
        Log.d("Price", "Price value: $price")


        // mise en marche du minuteur avant la fin de la session
        countdownTimer = object : CountDownTimer(initialCountdown, countdownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.timer.text = secondsRemaining.toString()
            }
            override fun onFinish() {
                binding.timer.text = "Temps écoulé !"
            }
        }
        countdownTimer.start() // lancement immédiat du minuteur

        // Animation
        val anim: AnimatedPieView = findViewById(R.id.pieView)
        val config: AnimatedPieViewConfig = AnimatedPieViewConfig()
        config.addData(
            SimplePieInfo(
                (2000.0 - name), //prix du lot moins valeur du pari
                Color.parseColor("#ADB7AE")
            )
        )
        config.addData(SimplePieInfo(name, Color.parseColor("#FCE300"))) // valeur du pari en jaune

        // Création d'un écouteur de données Firebase
        val messageListener = object : ValueEventListener {
            // Fonction appelée lorsqu'il y a un changement dans les données
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Récupération des données sous forme de Map<String, Any> depuis le snapshot
                val miseObject = dataSnapshot.value as? Map<String, Any>
                // Récupération de la première valeur de la Map (dans ce cas, la mise)
                val mise = miseObject?.values?.firstOrNull()
                // Vérification si le snapshot existe
                if (dataSnapshot.exists()) {
                    // Vérification si la valeur de la mise est de type Number
                    if (mise is Number) {
                        // Parcours de chaque enfant dans le snapshot
                        for (childSnapshot in dataSnapshot.children) {
                            val uid = childSnapshot.key // Récupérer l'ID de l'enfant (dans ce cas, l'ID utilisateur)
                            val value = childSnapshot.value
                            index++
                            val color = colors[index % colors.size]
                            if (value is Long) {
                            val lamise = value?.toDouble()
                            println("ca marche ${uid} ${value} ${lamise} ${color}")
                            println("Type de value: ${value?.javaClass}")

                            config.addData(SimplePieInfo(lamise, Color.parseColor(color)))
                            config.drawText(true)
                            config.strokeMode(false)
                            anim.applyConfig(config)
                            anim.start()
                        }}
                    }else {
                        println("La valeur récupérée n'est pas de type Double ${mise}")
                    }
                    } else {
                        println("Aucune donnée trouvée")
                        config.addData(SimplePieInfo((2000.0 - name), Color.parseColor("#ADB7AE")))
                        config.addData(SimplePieInfo(name, Color.parseColor("#FCE300"), "B"))
                        config.drawText(true)
                        config.strokeMode(false)
                        anim.applyConfig(config)
                        anim.start()
                    }
                }


            override fun onCancelled(databaseError: DatabaseError) {
                // En cas d'erreur lors de la récupération des données
                Log.e("Firebase", "Erreur lors de la récupération des données: ${databaseError.message}")
            }
        }

        val stakeRef = gameRef.child("Session $gamename").child("Stake")
        stakeRef.addValueEventListener(messageListener)



        fun refresh(valeurReturn: Double) {
            val intent = Intent(this, Gamble2::class.java)
            intent.putExtra("valeurReturn", valeurReturn)
            intent.putExtra("price", price)
            intent.putExtra("GameName", gamename)
            startActivity(intent)
        }

            binding.boutontest.setOnClickListener() {
                println("voila le nom $gamename")
                val user = FirebaseAuth.getInstance().currentUser
                val data: Double = 1000.0
                if (user != null) {
                    val sessionRef = gameRef.child("Session $gamename")
                    val playerRef = sessionRef.child("Players").child(user.uid)
                    playerRef.setValue(user.uid)

                    val stakeRef = sessionRef.child("Stake").child(user.uid)
                    stakeRef.setValue(data)

                    Log.d("valeur du paris", "La valeur introduite est ${if (name == 1000.0) 1000 else 100}")
                } else {
                    // Utilisateur non connecté
                    // Faire quelque chose pour gérer ce cas
                    println("Utilisateur non connecté")
                }
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

                Handler().postDelayed({
                    val intent = Intent(this, RedirectionWin::class.java)
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