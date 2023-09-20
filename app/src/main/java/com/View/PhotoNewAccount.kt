package com.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wheeler.databinding.ActivityPicNewAccountBinding
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ViewModel.PhotoNewAccountViewModel

class PhotoNewAccount : AppCompatActivity() {

    private lateinit var binding: ActivityPicNewAccountBinding
    private val REQUEST_IMAGE_PICK = 1
    private lateinit var viewModel: PhotoNewAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPicNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PhotoNewAccountViewModel::class.java)

        binding.addPic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }


        binding.Validate.setOnClickListener {
            val name = binding.Name.text.toString().trim()
            Log.d("debug", "Name: $name")

            val intent = Intent(this, PinsNewAccount::class.java)
            intent.putExtra("name", name)
                .putExtra("phonenumb", intent.getStringExtra("phonenumb"))
            startActivity(intent)
        }
    }

    // Navigationlistener?
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            if (selectedImageUri != null) {
                // Utilisez l'URI de l'image sélectionnée pour afficher l'image ou effectuer d'autres opérations
                Log.d("image", "une image a été ajouté")
            }
        }
    }
}
