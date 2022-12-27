package com.View

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.google.firebase.database.*
import java.lang.StringBuilder

class Prize : AppCompatActivity() {


    private lateinit var binding: ActivityPrizeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: DatabaseReference
    private lateinit var dialog: Dialog


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPrizeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    var database = FirebaseDatabase.getInstance().reference


    binding.button.setOnClickListener(){

        var test = binding.editText.text.toString()
        var data = binding.editText2.text.toString()

        database.child(test.toString()).setValue(Firebase(data))
    }}

}