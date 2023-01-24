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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlin.random.Random


open class Gamble : anychart() {

    var random = Random.nextInt(1,360)
    var randomDuration = Random.nextInt(1800,6000)
    var randomToFloat = random.toFloat()
    private lateinit var binding: ActivityAnychartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        binding = ActivityAnychartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val play = findViewById<Button>(R.id.play)
        val anim : AnimatedPieView = findViewById(R.id.pieView)
        val config : AnimatedPieViewConfig = AnimatedPieViewConfig()
        val name = intent.getDoubleExtra("valeur",1.1 )


        config.addData(SimplePieInfo(2000.0, Color.parseColor("#ADB7AE")))
        config.addData(SimplePieInfo(name, Color.parseColor("#FCE300"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()

       /* config.addData(SimplePieInfo(name, Color.parseColor("#FCE300"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start() */

        fun refresh(valeurReturn : Double){
            val intent = Intent(this, Gamble2::class.java)
            intent.putExtra("valeurReturn", valeurReturn)
            startActivity(intent)
        }


        button.setOnClickListener(){
            refresh(valeurReturn = 1000.0)
        }


        button2.setOnClickListener(){
            refresh(valeurReturn = 100.0)
        }


        play.setOnClickListener(){

                val animations =
                    ObjectAnimator.ofFloat(anim, "rotation", randomToFloat).apply {
                        duration = randomDuration.toLong()
                    }

                val set = AnimatorSet()
                set.playTogether(animations)
                set.start()
                win()
        }
    }

    private fun win(){
        var newrandom = random.toInt()

        for (x in 10..360){
        var ess = random % x
        if(ess == 0 ){
            println("gagné")
            binding.calcul.text = "gagné"
            binding.textview3.text = random.toString()
            break
        }
            else{
            println("perdu")
            binding.textview3.text = random.toString()

        }
    }
}}