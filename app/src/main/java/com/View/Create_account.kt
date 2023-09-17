package com.View

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity  // Importez FragmentActivity au lieu de AppCompatActivity
import androidx.lifecycle.Observer
import com.ViewModel.Create_accountViewModel
import com.ViewModel.LoginViewModel
import com.example.wheeler.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import java.text.SimpleDateFormat
import java.util.Locale

class Create_account : AppCompatActivity() {

    private lateinit var viewModel: Create_accountViewModel
    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance() // instance de l'authentification via Firebase Auth
        // Initialize your viewModel here
        viewModel = Create_accountViewModel()

        // Création du datePicker
        binding.date.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year1, month1, dayOfMonth ->
                    binding.date.setText(String.format("%02d/%02d/%04d", dayOfMonth, (month1 + 1), year1))
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        fun generateRandomCode(): String {
            val random = java.util.Random()
            val code = StringBuilder()

            for (i in 0 until 4) { // Générez 4 chiffres
                val digit = random.nextInt(10) // Générez un chiffre aléatoire entre 0 et 9
                code.append(digit)
            }

            return code.toString()
        }


        // bouton pour la validation des données entrées dans les deux input pour la création du compte
        binding.CreateAccount.setOnClickListener {
            // données entrées dans les inputs par les utilisateurs
            val email = binding.email.text.toString().trim()
            val codeVerification = generateRandomCode()
            Log.d("Create_accountViewModel", "onVerificationSuccess called with code: $codeVerification")

            // envoi vers le ViewModel des données entrées par les utilisateurs
           // viewModel.createAccount(email, password, birthdate, codeVerification)
            viewModel.sendEmailToUser(email, codeVerification)

        }

        // Écoute des événements de navigation et démarrage de la nouvelle activité si nécessaire
        viewModel.navigationListener = object : Create_accountViewModel.NavigationListener {
            override fun onVerificationSuccess(codeVerification: String) {
                Log.d("Create_accountViewModel", "onVerificationSuccess called with code: $codeVerification")
                // Démarrer l'activité PhoneNumberNewAccount ici
                val email = binding.email.text.toString().trim()
                val password = binding.password.text.toString().trim()
                val birthdate = binding.date.text.toString().trim()
                val phoneIntent = Intent(this@Create_account, EmailCheck::class.java)
                startActivity(phoneIntent
                    .putExtra ("codeVerification", codeVerification)
                    .putExtra ("email", email)
                    .putExtra ("password", password)
                    .putExtra ("birthdate", birthdate)
                )
            }

            override fun onEmailSentSuccessfully() {
                // Traitement à effectuer lorsque l'e-mail est envoyé avec succès
                Log.d("reussi", "bravo")
            }
            override fun onEmailNotSentSuccessfully() {
                // Traitement à effectuer lorsque l'e-mail est envoyé avec succès
                Log.d("raté", "l'utilisateur existe deja")
            }
        }


        viewModel.result.observe(this, Observer { result ->
            // Mettre à jour l'interface utilisateur en fonction du résultat (par exemple, afficher un toast)
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        })
    }
}











