package com.View

import android.content.Intent
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
import com.example.wheeler.databinding.ActivityAnychartBinding
import com.example.wheeler.databinding.LoginSubstituteBinding
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo


open class anychart : AppCompatActivity() {


    private lateinit var binding: ActivityAnychartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)


        binding = ActivityAnychartBinding.inflate(layoutInflater) //Layoutinflater ?
        setContentView(binding.root)

        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        var edit = findViewById<EditText>(R.id.editText)
        var text = findViewById<TextView>(R.id.text)
        var anim : AnimatedPieView = findViewById(R.id.pieView)
        var anim2 : AnimatedPieView = findViewById(R.id.pieView2)
        var config : AnimatedPieViewConfig = AnimatedPieViewConfig()



        config.addData(SimplePieInfo(2000.0, Color.parseColor("#AAFF0000")))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()


        fun newPie(valeur: Double){

        config.addData(SimplePieInfo(valeur, Color.parseColor("#000000"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()}



         fun refresh() {
            val intent = Intent(applicationContext, anychart::class.java)
            startActivity(intent)
            finish()
        }

        button.setOnClickListener(){

            newPie(valeur = 33.3)
        }



        button2.setOnClickListener(){

                refresh()
                newPie(valeur = 333.3)
            }



        } }







