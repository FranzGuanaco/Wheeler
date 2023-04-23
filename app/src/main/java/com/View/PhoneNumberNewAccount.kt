package com.View

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

class PhoneNumberNewAccount : AppCompatActivity() {

    lateinit var binding: ActivityPhoneNumberNewAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")
        val mail = intent.getStringExtra("mail")
        val password = intent.getStringExtra("password")
        val birth = intent.getIntExtra("birth", 0)
        val countrySpinner = binding.spinner

        val adapter = Spinner(this, resources.getStringArray(R.array.countries_with_codes).toList())

        countrySpinner.adapter = adapter

        countrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position) as String
                val countryInfo = selectedItem.split(",")
                val countryCode = countryInfo[0].trim()
                val countryDialCode = countryInfo[1].trim()

                // Affichez l'indicatif téléphonique du pays sélectionné dans un champ de texte ou une vue TextView
                // Par exemple, si vous avez un champ de texte ou une vue TextView avec l'ID phoneCodeTextView :
                binding.PhoneNumb.setText(countryDialCode)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Vous pouvez gérer ici le cas où aucun élément n'est sélectionné, si nécessaire
            }
        }

        binding.Validate.setOnClickListener() {
            val phoneNumberStr = binding.PhoneNumb.text.toString()
            val phoneNumber = phoneNumberStr.toInt()

            val newUser = hashMapOf(
                "name" to "John Doe",
                "email" to mail,
                "birthdate" to birth,
                "password" to password,
                "phone" to phoneNumber
            )

            usersCollection.add(newUser)
                .addOnSuccessListener { documentReference ->
                    Log.d("test", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("test", "Error adding document", e)
                }
        }
    }
}



