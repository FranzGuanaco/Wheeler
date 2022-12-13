package com.ViewModel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Model.DBHelper
import com.example.wheeler.R

class Login: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_substitute)

        val password = findViewById<EditText>(R.id.password)
        val id = findViewById<EditText>(R.id.user_name)
        val Name = findViewById<TextView>(R.id.Name)
        val Age = findViewById<TextView>(R.id.Age)
        val addName = findViewById<Button>(R.id.add)
        val printName = findViewById<Button>(R.id.print)


        addName.setOnClickListener{

            // below we have created a new DBHelper class, and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values in name and age edit texts
            val Pass = password.text.toString()
            val ID = id.text.toString()

            // calling method to add name to our database
            db.AddData(Pass, ID)

            // Toast to message on the screen
            Toast.makeText(this, Pass + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            password.text.clear()
            id.text.clear()
        }

        // below code is to add on  click listener to our print name button
        printName.setOnClickListener{

            // creating a DBHelper class and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor we have called method to get all names from our database and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and appending value in the text view
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ID)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PASSWORD)) + "\n")

            // moving our cursor to next position and appending values
            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ID)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PASSWORD)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}
