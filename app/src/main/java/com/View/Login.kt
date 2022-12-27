package com.View

import android.content.Intent
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
import com.ViewModel.Game
import com.ViewModel.LoginViewModel
import com.View.Prize
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
    private lateinit var binding: LoginSubstituteBinding //layout used in this activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginSubstituteBinding.inflate(layoutInflater) //Layoutinflater ?
        setContentView(binding.root)

        appDB = DataUsers.getDatabase(this) //Call

        binding.print.setOnClickListener {
           writeData()
        } //fonction pour inserer des données
    }

        private fun writeData(){

        var Name = binding.userName.text.toString() // Name =  text du widget


        if(Name.isNotEmpty()) {
            var user = User(
                null, Name.toString()  )   //new array
            GlobalScope.launch(Dispatchers.IO) {
                //coroutine ? Globalscope ??
                appDB.datalogin().insert(user)  //appDB variable representant le class dataUser qui a la fun datalogin qui est l'extension de detalogin interface
                // qui est l'extension de user qui a la fun insert
            }

            binding.userName.text.clear()


            if (Name.equals("PierreChev")) {
                val authentified = Intent(this, MainActivity::class.java)
                startActivity(authentified)
            }  else {
                val authentified = Intent(this, Prize::class.java)
                startActivity(authentified)
            }


            Toast.makeText(this@Login,"Successfully written",Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this,"PLease Enter Data",Toast.LENGTH_SHORT).show()
    }}


