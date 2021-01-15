package com.example.myapplication.ui.eclairage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class EclairFragment : Fragment() {

    private lateinit var eclairViewModel: EclairViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eclairViewModel =
            ViewModelProvider(this).get(EclairViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_eclairage, container, false)
        val textView: TextView = root.findViewById(R.id.textView5)
        eclairViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}