package com.View

import android.os.Bundle
import android.view.ViewDebug
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Model.DBHelper
import com.ViewModel.LoginViewModel
import com.example.wheeler.R

class Login: AppCompatActivity() {

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_substitute)
        val test = findViewById<EditText>(R.id.user_name)
        val button = findViewById<Button>(R.id.add)


        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        (viewModel as LoginViewModel).currentnum.observe(this, Observer { test.setText(it.toString()) })


    button.setOnClickListener()
    {
        var uno = 1

        (viewModel as LoginViewModel).currentnum.value = uno++


    }}}
