package com.View

import android.content.Intent
import android.os.Bundle
import com.example.wheeler.R
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.View.Spinner
import com.example.wheeler.databinding.ActivityPhoneNumberNewAccountBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class PhoneNumberNewAccount : AppCompatActivity() {

    lateinit var binding: ActivityPhoneNumberNewAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")
        val test = intent.getStringExtra("test") ?: "No email found"
        val mail = intent.getStringExtra("mail") ?: "No email found"
        val password = intent.getStringExtra("password") ?: "No password found"
        val birth = intent.getLongExtra("birth", 0)
        val birthDate = Date(birth)
        val countrySpinner = binding.spinner

        val adapter = Spinner(this, resources.getStringArray(R.array.countries_with_codes).toList())

        Log.e("test", mail)
        countrySpinner.adapter = adapter

        countrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                val countryInfo = selectedItem.split(",")
                val countryCode = countryInfo[0].trim()
                val countryDialCode = countryInfo[1].trim()

                binding.PhoneNumb.setText(countryDialCode)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Vous pouvez gérer ici le cas où aucun élément n'est sélectionné, si nécessaire
            }
        }

        binding.Validate.setOnClickListener() {
            val phoneNumberStr = binding.PhoneNumb.text.toString()
            val phoneNumberStrWithoutPlus = phoneNumberStr.replace("+", "")
            val phoneNumber = phoneNumberStrWithoutPlus

            // Log email and password for debugging purposes
            Log.d("debug", "Email: $mail")
            Log.d("debug", "Password: $password")
            Log.d("debug", "Birth: $birthDate")
            Log.d("debug", "test: $test")


            val newUser = hashMapOf(
                "name" to "Pierre", // Vous pouvez également récupérer le nom de l'utilisateur et le stocker ici.
                "email" to mail,
                "birthdate" to birthDate,
                "password" to password, // Storing passwords in Firestore is not recommended for security reasons.
                "phone" to phoneNumber
            )


            usersCollection.add(newUser)
                .addOnSuccessListener { documentReference ->
                    Log.d("test", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("test", "Error adding document", e)
                }

            val intent = Intent(this, PhoneNumberNewAccount::class.java) // Changez cette ligne pour rediriger vers l'activité souhaitée après la création du compte
            startActivity(intent)
        }
    }
}


