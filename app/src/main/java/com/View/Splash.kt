package com.View

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //variable se referant à chaque triangle
        val triangle1 = findViewById<ImageView>(R.id.triangleYellow)
        val triangle2 = findViewById<ImageView>(R.id.triangleYellow2)
        val triangle3 = findViewById<ImageView>(R.id.triangleRed)
        val triangle4 = findViewById<ImageView>(R.id.triangleRed2)
        val triangle5 = findViewById<ImageView>(R.id.triangleGreen)
        val triangle6 = findViewById<ImageView>(R.id.trianglePurple)
        val triangle7 = findViewById<ImageView>(R.id.triangleBlue)
        val triangle8 = findViewById<ImageView>(R.id.triangleBlue2)
        val login = findViewById<Button>(R.id.Loginbutton)
        val createAccount = findViewById<Button>(R.id.create_account)

// mouvement pour chaque triangle
        val objectAnimator1x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle1, "translationX", 1000f)
        val objectAnimator1y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle1, "translationY", 10f)

        val objectAnimator2x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle2, "translationX", -1000f)
        val objectAnimator2y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle2, "translationY", -10f)

        val objectAnimator3x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle3, "translationX", 1000f)
        val objectAnimator3y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle3, "translationY", 300f)

        val objectAnimator4x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle4, "translationX", -1000f)
        val objectAnimator4y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle4, "translationY", -600f)

        val objectAnimator5x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle5, "translationX", 100f)
        val objectAnimator5y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle5, "translationY", 1200f)

        val objectAnimator6x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle6, "translationX", 100f)
        val objectAnimator6y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle6, "translationY", -1200f)

        val objectAnimator7x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle7, "translationX", 800f)
        val objectAnimator7y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle7, "translationY", -1500f)

        val objectAnimator8x: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle8, "translationX", -900f)
        val objectAnimator8y: ObjectAnimator =
            ObjectAnimator.ofFloat(triangle8, "translationY", 1500f)

// déclenchement de toutes les animations
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator1x, objectAnimator1y, objectAnimator2x, objectAnimator2y, objectAnimator3x, objectAnimator3y, objectAnimator4x, objectAnimator4y, objectAnimator5x, objectAnimator5y,
                objectAnimator6x, objectAnimator6y, objectAnimator7x, objectAnimator7y, objectAnimator8x, objectAnimator8y)
        animatorSet.startDelay = 3000
        animatorSet.duration = 900

        animatorSet.start()

// bouton de login
        login.setOnClickListener() {
            val loginActivity = Intent(this, Login::class.java)
            startActivity(loginActivity)
        }

 //bouton de creation de compte
        createAccount.setOnClickListener(){
            val accountActivity = Intent(this, Create_account::class.java)
            startActivity(accountActivity)


            }}}