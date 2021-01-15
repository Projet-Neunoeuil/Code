package com.example.myapplication.ui.temperature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class GalleryFragment : Fragment() {

    private lateinit var temperatureViewModel: TemperatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        temperatureViewModel =
            ViewModelProvider(this).get(TemperatureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_temperature, container, false)
        val textView: TextView = root.findViewById(R.id.textView2)
        temperatureViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}