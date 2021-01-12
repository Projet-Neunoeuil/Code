package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var btn : Button =findViewById(R.id.buttonTemp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener() {
            fun onClick(view:View) {
                var i = Intent(this@MainActivity, Temperature::class.java)
                startActivity(i)
            }
        }
}
}