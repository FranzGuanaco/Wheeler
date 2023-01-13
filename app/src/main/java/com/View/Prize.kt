package com.View

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.adapters.ImageViewBindingAdapter
import com.ViewModel.Game
import com.bumptech.glide.Glide
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.StringBuilder
import kotlin.concurrent.thread

class Prize : AppCompatActivity() {


    private lateinit var binding: ActivityPrizeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: DatabaseReference
    private lateinit var dialog: Dialog


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPrizeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    var imageRef = FirebaseStorage.getInstance().reference

    var googlePlay =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2FStore.jpeg?alt=media&token=5c50a9ab-07b2-460b-b45f-0beb7c9eb056"
    Glide.with(baseContext).asBitmap().load(googlePlay).into(binding.GooglePay)

    Thread.sleep(10)

    var stay =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
    Glide.with(baseContext).asBitmap().load(stay).into(binding.StayPrize)

    Thread.sleep(10)

    var cinema =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fphoto-1574267432553-4b4628081c31.jpeg?alt=media&token=39dc7762-a5e5-41ad-8e6c-727680154b41"
    Glide.with(baseContext).asBitmap().load(cinema).into(binding.NetflixPrize)

    Thread.sleep(10)

    var xbox =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fxbox_price.jpeg?alt=media&token=a2a52487-2ecb-4c4b-a5f6-a67656258d60"
    Glide.with(baseContext).asBitmap().load(xbox).into(binding.PrimePrize)

    Thread.sleep(10)

    var castle =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
    Glide.with(baseContext).asBitmap().load(castle).into(binding.WeekendPrize)

    var other1 =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
    Glide.with(baseContext).asBitmap().load(castle).into(binding.other2)


    var other2 =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
    Glide.with(baseContext).asBitmap().load(castle).into(binding.other1)


    var other3 =
        "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
    Glide.with(baseContext).asBitmap().load(castle).into(binding.other3)


}}


