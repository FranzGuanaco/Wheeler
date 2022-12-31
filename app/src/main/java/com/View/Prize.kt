package com.View

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.ViewModel.Game
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.File
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

    binding.button.setOnClickListener(){
        val intent = Intent(this, anychart::class.java)
        startActivity(intent)
    }

    /*
    var database = FirebaseDatabase.getInstance().reference

    /*val progressDialog = ProgressDialog(this)
    progressDialog.setMessage("fetch")
    progressDialog.setCancelable(false)
    progressDialog.show() */


        val storage = FirebaseStorage.getInstance().reference.child("Image/Store.jpeg")
        val storage2 = FirebaseStorage.getInstance().reference.child("Image/cine.jpeg")
        val storage3 = FirebaseStorage.getInstance().reference.child("Image/Store.jpeg")
        val storage4 = FirebaseStorage.getInstance().reference.child("Image/Store.jpeg")

        val localfile = File.createTempFile("temp", "jpg")
        storage.getFile(localfile).addOnSuccessListener {



            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.imageView.setImageBitmap(bitmap)


        }

    storage2.getFile(localfile).addOnSuccessListener {

        val bitmap2 = BitmapFactory.decodeFile(localfile.absolutePath)
         binding.imageView2.setImageBitmap(bitmap2)

    }

    } */





}}


