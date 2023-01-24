package com.View


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlin.random.Random


open class Gamble2 : anychart() {

    private lateinit var binding: ActivityAnychartBinding

    var random = Random.nextInt(1800,3600)
    var randomDuration = Random.nextInt(1800,6000)
    var randomToFloat = random.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)


        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        var play = findViewById<Button>(R.id.play)
        var anim: AnimatedPieView = findViewById(R.id.pieView)
        var config: AnimatedPieViewConfig = AnimatedPieViewConfig()
        val name2 = intent.getDoubleExtra("valeurReturn", 1.1)
        var text = findViewById<TextView>(R.id.textview3)



        config.addData(SimplePieInfo(2000.0, Color.parseColor("#ADB7AE")))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()


        config.addData(SimplePieInfo(name2, Color.parseColor("#FCE300"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()


        fun refresh2(valeur: Double) {
            val intent = Intent(this, Gamble::class.java)
            intent.putExtra("valeur", valeur)
            startActivity(intent)
        }


        button.setOnClickListener() {
            refresh2(valeur = 1000.0)
        }

        button2.setOnClickListener() {
            refresh2(valeur = 100.0)
        }

        play.setOnClickListener() {

            val animations =
                ObjectAnimator.ofFloat(anim, "rotation", randomToFloat).apply {
                    duration = randomDuration.toLong()
                }

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()

            }
        }
    }