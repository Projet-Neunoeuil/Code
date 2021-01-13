package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    var btn: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById<View>(R.id.imageThermo) as ImageButton
        btn!!.setOnClickListener {
            val i = Intent(this@MainActivity, Temperatures::class.java)
            startActivity(i)
        }
    }
}