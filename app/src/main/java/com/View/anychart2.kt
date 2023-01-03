package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import java.util.stream.IntStream
import kotlin.random.Random


class anychart2 : anychart() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        var button = findViewById<Button>(R.id.button)
        var text = findViewById<TextView>(R.id.text)
        chart = findViewById(R.id.pie)
        configChartView(newValue)

        var random = Random.nextInt(360, 3600)
        var random2 = random.toFloat()
        var list = arrayOf(360, 180, 90)




                var tes = list.random()
                var cif = tes.toFloat()





         fun game(){

            if(tes == 360) {

                text.setText("gagné")
            }

             else {
                text.setText("perdu")
            }
        }

        button.setOnClickListener(){
       val animations =
            ObjectAnimator.ofFloat(chart, "rotation", 0f, cif).apply {
                duration = 1800
            }

        val set = AnimatorSet()
        set.playTogether(animations)
        set.start()
        game()}

}}





