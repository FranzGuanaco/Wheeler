package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R
import kotlin.random.Random


class anychart2 : anychart() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        var button = findViewById<Button>(R.id.button)
        chart = findViewById(R.id.pie)
        configChartView()

        var random = Random.nextInt(360, 3600).toFloat()

        button.setOnClickListener(){
       val animations =
            ObjectAnimator.ofFloat(chart, "rotation", 0f, random).apply {
                duration = 1800
            }

        val set = AnimatorSet()
        set.playTogether(animations)
        set.start()}

}}





