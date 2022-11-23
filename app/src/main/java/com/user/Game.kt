package com.example.draft

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Interpolator
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.draft.databinding.FragmentFirstBinding
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider
import java.text.DateFormat
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Game : AppCompatActivity()
{


    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start)


        // Receiving activity from MainActivity
        val intent = intent
        val name = intent.getIntExtra("jauge", 1)
        val bar = findViewById<ProgressBar>(R.id.progressBar2)
        val button = findViewById<Button>(R.id.button)
        val cursor = findViewById<ImageView>(R.id.imageView4)
        val text = findViewById<EditText>(R.id.settext2)


        bar.max = 100 // remplissage maximale de la barre
        bar.progress = name // donnée de la MainActivity




        fun test() {


            val array  = intArrayOf(1,3,4,22)

            for (n in array){

                if (n == name){
                    text.setText("Winner")

                    val animations = arrayOf(2000f, -1000f).map { translation ->
                        ObjectAnimator.ofFloat(cursor, "translationX", translation).apply {
                            duration = 800
                            repeatCount = 12
                            repeatMode = ObjectAnimator.REVERSE
                        }}

                    val set = AnimatorSet()
                    set.playTogether(animations)
                    set.start()
                }



                else{
                    text.setText("$name")}}}



        button.setOnClickListener(){

            test()
        }}}

