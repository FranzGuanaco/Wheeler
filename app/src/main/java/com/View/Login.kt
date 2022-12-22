package com.View

import android.os.Bundle
import android.util.Log
import android.view.ViewDebug
import android.widget.*
import androidx.room.Entity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Model.DBHelper
import com.Model.DataUsers
import com.Model.User
import com.ViewModel.LoginViewModel
import com.example.wheeler.R
import com.example.wheeler.databinding.LoginBinding
import com.example.wheeler.databinding.LoginSubstituteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Login: AppCompatActivity() {

    lateinit var viewModel: ViewModel
    lateinit var appDB: DataUsers
    private lateinit var binding: LoginSubstituteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginSubstituteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = DataUsers.getDatabase(this)

        binding.print.setOnClickListener {
            writeData()
        }


    }

    private fun writeData(){

        val Name = binding.userName.text.toString()

        if(Name.isNotEmpty()    ) {
            val user = User(
                null, Name.toString()
            )
            GlobalScope.launch(Dispatchers.IO) {

                appDB.datalogin().insert(user)
            }

            binding.userName.text.clear()

            Toast.makeText(this@Login,"Successfully written",Toast.LENGTH_SHORT).show()
        }else Toast.makeText(this@Login,"PLease Enter Data",Toast.LENGTH_SHORT).show()

    }
    }


