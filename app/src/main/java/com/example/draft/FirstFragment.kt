package com.example.draft

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Interpolator
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.draft.databinding.FragmentFirstBinding
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : AppCompatActivity() {


    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start)



        // Receiving activity from MainActivity
        val intent = intent
        val name = intent.getIntExtra("jauge", 1)
        val bar = findViewById<ProgressBar>(R.id.progressBar2)
        val button = findViewById<Button>(R.id.button)
        val cursor = findViewById<ImageView>(R.id.imageView4)
        var random = 1





        bar.max = 100
        bar.progress = name


        button.setOnClickListener(){

            random = Random.nextInt(1,5)
            val animations = arrayOf(1000f, -600f).map { translation ->
                ObjectAnimator.ofFloat(cursor, "translationX", translation).apply {
                    duration = 800
                    repeatCount = random
                    repeatMode = ObjectAnimator.REVERSE



                }



            }

            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()





                } }}













