package com.order

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.draft.R

class Main  : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_gamble)


        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        val converter:  EditText = findViewById (R.id.gamblesum)
        val toast = Toast.makeText(applicationContext, "pas possible", Toast.LENGTH_SHORT)
        toast.setGravity(2, 90, 0) // pas important pour le moment
        val newpage = findViewById<Button>(R.id.validation)
        var prog = 0




        progressbar.max= 100;


        converter.setOnClickListener()

        {

            // boutton pour choisir la quantité d'argent à mettre

            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() < 200 ){
                with(progressbar) { progress= (converter.text.toString().toInt() * 100 / 200)
                    // prog c'est la variable qui reprend la quantité d'argent misé par l'utilisateur
                    prog = progress
                } }

            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() > 200 ){
                toast.show()

                // toast pour informer de la mise maximale (ne fonctionne pas)
            }
        }

        newpage?.setOnClickListener(){
            if ( converter.text.toString().toDoubleOrNull() is Double && converter.text.toString().toDouble() < 200 ){
                val intent = Intent(this,Result::class.java)
                intent.putExtra("jauge", prog)
                startActivity(intent)

                // intend une fois que la mise a été validé
            }
        }
    }
}