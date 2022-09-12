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
class FirstFragment : AppCompatActivity()
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
        var text = findViewById<EditText>(R.id.settext)




        bar.max = 100
        bar.progress = name




        fun test() {

            val animations = arrayOf(1000f, -600f).map { translation ->
                ObjectAnimator.ofFloat(cursor, "translationX", translation).apply {
                    duration = 800
                    repeatCount = 122
                    repeatMode = ObjectAnimator.REVERSE
                } }

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()


            val random = Random.nextLong(1000,15000)

            object : CountDownTimer(random, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    text.setText("seconds remaining: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {
                    set.cancel()
                }
            }.start()
        }

        button.setOnClickListener(){

            test()
        }}}



// faire que la fleche identifie si elle est sur la progressbar ou non
// faire que la fleche s'arrete aleatoirement => func minuteur










