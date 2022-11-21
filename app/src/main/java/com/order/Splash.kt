package com.order

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.draft.R
import kotlinx.coroutines.delay


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val triangle1 = findViewById<ImageView>(R.id.triangleYellow)
        val triangle2 = findViewById<ImageView>(R.id.triangleYellow2)
        val triangle3 = findViewById<ImageView>(R.id.triangleRed)
        val triangle4 = findViewById<ImageView>(R.id.triangleRed2)
        val triangle5 = findViewById<ImageView>(R.id.triangleGreen)
        val triangle6 = findViewById<ImageView>(R.id.trianglePurple)
        val triangle7 = findViewById<ImageView>(R.id.triangleBlue)
        val triangle8 = findViewById<ImageView>(R.id.triangleBlue2)




        val animations = arrayOf(2000f, 1000f).map { translation ->
            ObjectAnimator.ofFloat(triangle1, "translationX", translation).apply {
                duration = 1000
                startDelay = 2000


            }}

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()

        }}