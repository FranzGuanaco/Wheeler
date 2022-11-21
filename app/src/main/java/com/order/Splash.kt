package com.order

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.draft.R


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


        val objectAnimator1: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle1, "translationX", 1000f)
        val objectAnimator2: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle2, "translationX", -1000f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator1, objectAnimator2)
        animatorSet.startDelay = 1000
        animatorSet.duration = 2000

        animatorSet.start()


    }}