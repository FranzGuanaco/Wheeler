package com.View

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage


 class MainActivity : AppCompatActivity() {

//BINDING ???

        private lateinit var databaseReference: DatabaseReference
        private lateinit var storageReference: DatabaseReference
        lateinit var binding: ActivityPrizeBinding
        lateinit var image: String

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_prize)
             binding = ActivityPrizeBinding.inflate(layoutInflater)
            setContentView(binding.root)

            var GooglePay = findViewById<ImageView>(R.id.GooglePay)
            var Stayprize = findViewById<ImageView>(R.id.StayPrize)
            var NetflixPrize = findViewById<ImageView>(R.id.NetflixPrize)
            var PrimePrize = findViewById<ImageView>(R.id.PrimePrize)
            var WeekendPrize = findViewById<ImageView>(R.id.WeekendPrize)
            var other_2 = findViewById<ImageView>(R.id.other2)
            var other_1 = findViewById<ImageView>(R.id.other1)
            var other_3 = findViewById<ImageView>(R.id.other3)
            var SpotifyPrize = findViewById<ImageView>(R.id.SpotifyPrize)
            var Rio = findViewById<ImageView>(R.id.Rio)
            var Mexico = findViewById<ImageView>(R.id.Mexico)
            var VideoGame = findViewById<ImageView>(R.id.VideoGame)
            var logout = findViewById<ImageButton>(R.id.logout)


            cinema()

            var test = Create_account()
            test.auth = FirebaseAuth.getInstance()

            logout.setOnClickListener(){
                test.out()
                var intent = Intent(this, Create_account::class.java)
                startActivity(intent)

            }

            cinema()
            Thread.sleep(10)


            var imageRef = FirebaseStorage.getInstance().reference

            var googlePlay =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2FStore.jpeg?alt=media&token=5c50a9ab-07b2-460b-b45f-0beb7c9eb056"
            Glide.with(baseContext).asBitmap().load(googlePlay).into(GooglePay)

            Thread.sleep(10)

            var stay =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
            Glide.with(baseContext).asBitmap().load(stay).into(Stayprize)

            Thread.sleep(10)


            var xbox =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fxbox_price.jpeg?alt=media&token=a2a52487-2ecb-4c4b-a5f6-a67656258d60"
            Glide.with(baseContext).asBitmap().load(xbox).into(PrimePrize)

            Thread.sleep(10)

            var castle =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
            Glide.with(baseContext).asBitmap().load(castle).into(WeekendPrize)

            var other1 =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
            Glide.with(baseContext).asBitmap().load(other1).into(other_2)


            var other2 =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
            Glide.with(baseContext).asBitmap().load(other2).into(other_1)


            var other3 =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.jpeg?alt=media&token=b97b65a9-8968-4983-b465-a3e36622c84d"
            Glide.with(baseContext).asBitmap().load(other3).into(other_3)

            Thread.sleep(10)

            var spotify =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
            Glide.with(baseContext).asBitmap().load(spotify).into(SpotifyPrize)

            Thread.sleep(10)

            var rio =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
            Glide.with(baseContext).asBitmap().load(rio).into(Rio)

            Thread.sleep(10)

            var mex =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
            Glide.with(baseContext).asBitmap().load(mex).into(Mexico)

            Thread.sleep(10)

            var video =
                "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
            Glide.with(baseContext).asBitmap().load(video).into(VideoGame)


            NetflixPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Successfully written",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)
                image = "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fstay_prize.jpeg?alt=media&token=9c6f0296-64b0-4997-b7b4-97cc45f9efa2"
                intent.putExtra("img", image)
                startActivity(intent)
            }}


            private fun cinema(){
                var cinema =
                    "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fphoto-1574267432553-4b4628081c31.jpeg?alt=media&token=39dc7762-a5e5-41ad-8e6c-727680154b41"
                Glide.with(baseContext).asBitmap().load(cinema).into(binding.NetflixPrize)}
        }

