package com.View

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wheeler.R
import com.example.wheeler.databinding.ActivityPrizeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


 class MainActivity : AppCompatActivity() {

        private lateinit var databaseReference: DatabaseReference
        private lateinit var storageReference: DatabaseReference
        lateinit var binding: ActivityPrizeBinding
        lateinit var image: String

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_prize)
             binding = ActivityPrizeBinding.inflate(layoutInflater)
            setContentView(binding.root)


            netflix()
            Thread.sleep(10)
            googleplay()
            Thread.sleep(10)
            prime()
            Thread.sleep(10)
            spotify()
            Thread.sleep(10)
            travel()
            Thread.sleep(10)
            tokyo()
            Thread.sleep(10)
            london()
            Thread.sleep(10)
            castle()
            Thread.sleep(10)
            best1()
            Thread.sleep(10)
            best2()
            Thread.sleep(10)
            best3()
            Thread.sleep(10)
            best4()

            var test = Create_account()
            test.auth = FirebaseAuth.getInstance()

            binding.logout.setOnClickListener(){
                test.out()
                var intent = Intent(this, Create_account::class.java)
                startActivity(intent)

            }



            var imageRef = FirebaseStorage.getInstance().reference


            binding.NetflixPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Netflix")
                dataChild.child("imgNetflix").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent) }
                }

            }

            binding.GooglePlayPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)


                   var parent = FirebaseDatabase.getInstance().getReference("img")
                        var dataChild = parent.child("Google")
                        dataChild.child("imgGoogle").get().addOnSuccessListener {
                            Log.i("firebase", "Got value ${it.value}")
                            var pic = "${it.value}"
                            intent.putExtra("img", pic)

                            dataChild.child("data").get().addOnSuccessListener {
                                Log.i("firebase", "Got value ${it.value}")
                                var figure = "${it.value}"
                                intent.putExtra("figure", figure)

                                startActivity(intent) }
                        }
                }



            binding.SpotifyPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Spotify")
                dataChild.child("imgSpotify").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent)
            }}}

            binding.PrimePrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Prime")
                dataChild.child("imgPrime").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent)
            }}}

            binding.TravelPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Travel")
                dataChild.child("imgTravel").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent)
                    }
                }
            }

            binding.TokyoPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Tokyo")
                dataChild.child("imgTokyo").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent)
                    }
                }
            }

            binding.LondonPrize.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("London")
                dataChild.child("imgLondon").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent) }
                }
            }


            binding.CastlePrize.setOnClickListener() {

                Toast.makeText(this@MainActivity, "Good choice", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Castle")
                dataChild.child("imgCastle").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent)
                    }
                }
            }

            binding.Best1.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Google")
                dataChild.child("imgGoogle").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent) }
            }}

            binding.Best2.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)

                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("London")
                dataChild.child("imgLondon").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent) }
            }}

            binding.Best3.setOnClickListener(){

                Toast.makeText(this@MainActivity,"Good choice",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Choice::class.java)


                var parent = FirebaseDatabase.getInstance().getReference("img")
                var dataChild = parent.child("Tokyo")
                dataChild.child("imgTokyo").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    var pic = "${it.value}"
                    intent.putExtra("img", pic)

                    dataChild.child("data").get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")
                        var figure = "${it.value}"
                        intent.putExtra("figure", figure)

                        startActivity(intent)
                    }
            }


        }}




      private fun googleplay() {
          var googlePlay =
                    "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fgoogle%20play.png?alt=media&token=c0bce8e7-9b3d-4597-9233-975262ba35bb"
          Glide.with(baseContext).asBitmap().load(googlePlay).into(binding.GooglePlayPrize)
            }

     private fun netflix() {
         var netflix =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2FComponent%201.png?alt=media&token=859b98f4-a6dd-4edd-a678-367f397dd201"
         Glide.with(baseContext).asBitmap().load(netflix).into(binding.NetflixPrize)
        }

     private fun spotify() {
         var spot =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fspotify.png?alt=media&token=c3facf8c-b820-44c8-92dd-8e87ac950798"
         Glide.with(baseContext).asBitmap().load(spot).into(binding.SpotifyPrize)
     }

     private fun prime() {
         var prime =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fprimev.png?alt=media&token=4b70b2f9-4ec0-44a0-afe2-d7c4f40f122b"
         Glide.with(baseContext).asBitmap().load(prime).into(binding.PrimePrize)
     }

     private fun travel() {
         var trav =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Ftravel.png?alt=media&token=36e86672-8e58-4aec-b5e3-3333040d3f88"
         Glide.with(baseContext).asBitmap().load(trav).into(binding.TravelPrize)
     }

     private fun tokyo() {
         var tok =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Ftokyo.png?alt=media&token=3f6290a9-4845-4378-b852-acd47a7a3062"
         Glide.with(baseContext).asBitmap().load(tok).into(binding.TokyoPrize)
     }

     private fun london() {
         var lond =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2FLondon.png?alt=media&token=0491ca35-861d-431e-af04-514dc921a52c"
         Glide.with(baseContext).asBitmap().load(lond).into(binding.LondonPrize)
     }

     private fun castle() {
         var castle =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fcastle.png?alt=media&token=4140b6fc-576b-4fef-8398-5e9cc29f1a1f"
         Glide.with(baseContext).asBitmap().load(castle).into(binding.CastlePrize)
     }

     private fun best1() {
         var b1 =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fgoogle%20play.png?alt=media&token=c0bce8e7-9b3d-4597-9233-975262ba35bb"
         Glide.with(baseContext).asBitmap().load(b1).into(binding.Best1)
     }

     private fun best2() {
         var b2 =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2FLondon.png?alt=media&token=0491ca35-861d-431e-af04-514dc921a52c"
         Glide.with(baseContext).asBitmap().load(b2).into(binding.Best2)
     }

     private fun best3() {
         var b3 =
             "https://firebasestorage.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Ftokyo.png?alt=media&token=3f6290a9-4845-4378-b852-acd47a7a3062"
         Glide.with(baseContext).asBitmap().load(b3).into(binding.Best3)
     }

     private fun best4() {
         var b4 =
             "https://.googleapis.com/v0/b/wheeler-d6e1d.appspot.com/o/Image%2Fxbox.png?alt=media&token=7009da2f-7ff4-4e7c-a391-377097016e67"
         Glide.with(baseContext).asBitmap().load(b4).into(binding.Best4)
     }

 }




