package com.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.R

class Gamble : anychart() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)

        chart = findViewById(R.id.pie)
        var button = findViewById<Button>(R.id.button)
        var valeur = findViewById<EditText>(R.id.editText)

        configChartView(newValue = 1)

}}