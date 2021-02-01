package fr.unilim.aqualala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.myapplication.R

class ModificationEclair : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modification_eclairage)
        val heure = resources.getStringArray(R.array.Heure)
        val heureDeb: Spinner = findViewById(R.id.spinnerHeureDeb)

        if (heureDeb != null) {
            val adapter = ArrayAdapter(this,
                    R.layout.activity_modification_eclairage, heure)
            heureDeb.adapter = adapter

            heureDeb.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@ModificationEclair,
                            getString(R.string.selected_item) + " " +
                                    "" + heure[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //Ne rien faire
                }
            }
        }
    }
}