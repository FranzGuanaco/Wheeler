package com.View

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.wheeler.R
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo


open class anychart : Gamble() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)


        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        var edit = findViewById<EditText>(R.id.editText)
        var text = findViewById<TextView>(R.id.text)
        var config : AnimatedPieViewConfig = AnimatedPieViewConfig()
        var anim : AnimatedPieView = findViewById(R.id.pieView)


        var chiffre = edit.text.toString()


        button.setOnClickListener(){
            valeur = 220.2

            config.addData(SimplePieInfo(2000.0, Color.parseColor("#AAFF0000"), "A"))
            config.addData(SimplePieInfo(valeur, Color.parseColor("#000000"), "B"))
            config.drawText(true)
            config.strokeMode(false)

            anim.applyConfig(config)
            anim.start()}


        button2.setOnClickListener(){
            valeur = 22.2

        config.addData(SimplePieInfo(2000.0, Color.parseColor("#AAFF0000"), "A"))
        config.addData(SimplePieInfo(valeur, Color.parseColor("#000000"), "B"))
        config.drawText(true)
        config.strokeMode(false)

        anim.applyConfig(config)
        anim.start()}

    }}