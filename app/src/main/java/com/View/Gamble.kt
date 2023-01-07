package com.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.example.wheeler.R
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo


open class Gamble : anychart() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anychart)


        var button = findViewById<Button>(R.id.button)
        var returned = findViewById<Button>(R.id.returned)
        var button2 = findViewById<Button>(R.id.button2)
        var next = findViewById<Button>(R.id.next)
        var anim : AnimatedPieView = findViewById(R.id.pieView)
        var config : AnimatedPieViewConfig = AnimatedPieViewConfig()
        var name = intent.getDoubleExtra("valeur",1.1 )


        config.addData(SimplePieInfo(2000.0, Color.parseColor("#AAFF0000")))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()


        config.addData(SimplePieInfo(name, Color.parseColor("#000000"), "B"))
        config.drawText(true)
        config.strokeMode(false)
        anim.applyConfig(config)
        anim.start()

        fun refresh(valeurReturn : Double){
            val intent = Intent(this, Gamble2::class.java)
            intent.putExtra("valeurReturn", valeurReturn)
            startActivity(intent)
        }

        fun returned(){
            val intent = Intent(this, anychart::class.java)
            startActivity(intent)
        }

        fun nextactivity(){
            val intent = Intent(this, anychart2::class.java)
            intent.putExtra("valNext", name)
            startActivity(intent)
        }


        button.setOnClickListener(){
            refresh(valeurReturn = 1000.0)
        }

        button2.setOnClickListener(){
            refresh(valeurReturn = 100.0)
        }

        returned.setOnClickListener(){
            returned()
        }

        next.setOnClickListener(){
            nextactivity()
        }

}
}