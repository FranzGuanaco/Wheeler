package com.example.wheeler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.ToggleButton

class account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)


        val switchToggle = findViewById<Switch>(R.id.switch_toggle)
        switchToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Le switch est activé
            } else {
                // Le switch est désactivé
            }
        }


}}