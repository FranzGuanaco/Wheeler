package com.user


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
import java.util.*
import java.util.stream.IntStream
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis
import com.example.draft.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Result : AppCompatActivity() {

}




        /*



        val animations = arrayOf(1000f, -600f).map { translation ->
            ObjectAnimator.ofFloat(cursor, "translationX", translation).apply {
                duration = 800
                repeatCount = 122
                repeatMode = ObjectAnimator.REVERSE
            }
        }   // mouvement de la fleche (useless pour le moment)

        val set = AnimatorSet()
        set.playTogether(animations)
        set.start()


    }  */





// faire que la fleche identifie si elle est sur la progressbar ou non
// faire que la fleche s'arrete aleatoirement => func minuteur

/*
object : CountDownTimer(random, 1000) {

    override fun onTick(millisUntilFinished: Long) {
        text.setText("seconds remaining: " + millisUntilFinished / 1000)
    }

    override fun onFinish() {
        set.cancel()  // annulation du mouvement de la fleche

    } */