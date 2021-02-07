package fr.unilim.aqualala.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.aqualala.R

class Eclairage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_eclairage)
        val btn : Button = findViewById<View>(R.id.buttonModifierEclair) as Button
    }
}