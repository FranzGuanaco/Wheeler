package com.example.draft.fragmentsall

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.draft.R

class Gamble: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_gamble)

        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        progressbar.visibility= View.VISIBLE
        val button = findViewById<Button>(R.id.button5)

        button.setOnClickListener{
            progressbar.incrementProgressBy(10)
}}}