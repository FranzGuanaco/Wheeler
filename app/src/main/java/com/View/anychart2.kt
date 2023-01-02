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
        configChartView()

       val animations =
            ObjectAnimator.ofFloat(chart, "rotation", 0f, 1060f).apply {
                duration = 1800
                repeatCount = 12
            }

        val set = AnimatorSet()
        set.playTogether(animations)
        set.start()

}}





