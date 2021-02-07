package fr.unilim.aqualala.view.eclairage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.unilim.aqualala.controller.ModificationEclairage
import com.example.aqualala.R

class EclairFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_eclairage, container, false)
        val textView: TextView = root.findViewById(R.id.textView5)
         activity?.setContentView(R.layout.fragment_eclairage)
         val btn : Button = activity?.findViewById<View>(R.id.buttonModifierEclair) as Button
         btn.setOnClickListener {
             val i = Intent(activity, ModificationEclairage::class.java)
             startActivity(i)
         }
        return root
    }




}

