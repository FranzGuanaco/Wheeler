package com.order

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.draft.Game
import com.example.draft.R


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_gamble)



        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        val converter:  EditText = findViewById (R.id.gamblesum)
        val toast = Toast.makeText(applicationContext, "pas possible", Toast.LENGTH_SHORT)
        toast.setGravity(2, 90, 0)
        val newpage = findViewById<Button>(R.id.validation)
        var prog = 0




        progressbar.max= 100;


        converter.setOnClickListener()

        {

            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() < 200 ){
                with(progressbar) { progress= (converter.text.toString().toInt() * 100 / 200)
                    prog = progress
                } }

            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() > 200 ){
                toast.show()
            }
        }

        newpage?.setOnClickListener(){
            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() < 200 ){
                val intent = Intent(this, Game::class.java)
                intent.putExtra("jauge", prog)
                startActivity(intent)
            }
        }
    }
}