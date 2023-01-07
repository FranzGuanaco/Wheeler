package com.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ViewModel.Game
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.wheeler.R
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo


open class anychart : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)


        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        var edit = findViewById<EditText>(R.id.editText)
        var text = findViewById<TextView>(R.id.text)
        var anim : AnimatedPieView = findViewById(R.id.pieView)
        var config : AnimatedPieViewConfig = AnimatedPieViewConfig()
        


        config.addData(SimplePieInfo(2000.0, Color.parseColor("#AAFF0000")))
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()



        fun newPie(valeur: Double){

        config.addData(SimplePieInfo(valeur, Color.parseColor("#000000")))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()}



         fun refresh(valeur : Double) {
             val intent = Intent(this, Gamble::class.java)
             intent.putExtra("valeur", valeur)
             startActivity(intent)
        }


        button.setOnClickListener(){
            refresh(valeur = 1000.0)
        }



        button2.setOnClickListener(){
                refresh(valeur = 100.0)
            }



        } }







