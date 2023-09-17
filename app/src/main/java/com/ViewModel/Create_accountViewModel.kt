package com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class Create_accountViewModel : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    interface NavigationListener {
        fun onVerificationSuccess(codeVerification: String)
        fun onEmailSentSuccessfully()
        fun onEmailNotSentSuccessfully()
    }

    var navigationListener: NavigationListener? = null


    fun sendEmailToUser(email: String, codeVerification: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Vérifiez si l'utilisateur existe déjà avec l'adresse e-mail donnée
                val authResult =
                    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).await()

                if (authResult.signInMethods?.isEmpty() == true) {
                    // L'utilisateur n'existe pas, vous pouvez maintenant créer le compte

                    // Effectuer l'envoi d'e-mail dans un thread séparé
                    val properties = Properties()
                    properties.put("mail.smtp.host", "smtp.gmail.com")
                    properties.put("mail.smtp.port", "587")
                    properties.put("mail.smtp.auth", "true")
                    properties.put("mail.smtp.starttls.enable", "true")

                    val session = Session.getDefaultInstance(properties, object : Authenticator() {
                        override fun getPasswordAuthentication(): PasswordAuthentication {
                            return PasswordAuthentication(
                                "chevin.pierre.tomas@gmail.com",
                                "dhdyefdnerzgivcz"
                            )
                        }
                    })

                    val message = MimeMessage(session)
                    message.setFrom(InternetAddress("chevin.pierre.tomas@gmail.com"))
                    message.addRecipient(Message.RecipientType.TO, InternetAddress(email))
                    message.subject = "Code de vérification"
                    message.setText("Votre code de vérification est : $codeVerification")

                    // Envoyez l'e-mail
                    Transport.send(message)

                    // L'e-mail a été envoyé avec succès
                    withContext(Dispatchers.Main) {
                        navigationListener?.onVerificationSuccess(codeVerification)
                        navigationListener?.onEmailSentSuccessfully()
                    }
                } else {
                    // L'utilisateur existe déjà, vous pouvez gérer cela ici
                    navigationListener?.onEmailNotSentSuccessfully()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Une erreur s'est produite lors de l'envoi de l'e-mail ou de la vérification
                withContext(Dispatchers.Main) {
                    _result.postValue("Failed to send email or check user.")
                }
            }
        }
    }
}