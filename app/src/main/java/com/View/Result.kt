package com.Controller


import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

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