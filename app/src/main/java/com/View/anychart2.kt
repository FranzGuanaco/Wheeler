package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R


class anychart2 : anychart() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        chart = findViewById(R.id.pie)
        var bouton = findViewById<Button>(R.id.button)
        configChartView()

       /* bouton.setOnClickListener(){
            inheritance()} */
    }



     /*   fun inheritance() {
            var test = configChartView()
            var camembert = pie

            val animations = arrayOf(2000f, -1000f).map { translation ->
                ObjectAnimator.ofFloat(chart, "translationX", translation).apply {
                    duration = 800
                    repeatCount = 12
                    repeatMode = ObjectAnimator.REVERSE
                }}

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start() */


    }





