package com.example.draft

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.draft.databinding.ActivityMainBinding
import com.example.draft.fragmentsall.Homefrag

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_first)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(androidx.fragment.R.id.fragment_container_view_tag, Homefrag())
        transaction.addToBackStack(null)
        transaction.commit()
    }}
