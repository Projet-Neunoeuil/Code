package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Temperatures : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_temperature)
    }
}