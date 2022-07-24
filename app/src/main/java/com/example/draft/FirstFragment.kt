package com.example.draft

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.draft.databinding.FragmentFirstBinding

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

        bar.max = 100

        button.setOnClickListener(){
            with(bar) { progress = name }
        }

    }




}






