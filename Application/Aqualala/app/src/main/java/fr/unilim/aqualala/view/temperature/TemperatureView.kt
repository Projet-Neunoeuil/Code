package fr.unilim.aqualala.view.temperature

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aqualala.R

class TemperatureView {
    var valeur: TextView? = null
    var commentaire: TextView? = null
    var temps: TextView? = null
    var msgErreur: TextView? = null

    fun initTextView(view: View) {
        //propriétés
        valeur = view.findViewById<View>(R.id.temperatureValeur) as TextView
        commentaire = view.findViewById<View>(R.id.commentaireTemperature) as TextView
        temps = view.findViewById<View>(R.id.valeurTemps) as TextView
        msgErreur = view.findViewById<View>(R.id.erreur) as TextView
    }

    fun changerCouleurTexte(codeCouleur: String) {
        valeur!!.setTextColor(Color.parseColor(codeCouleur))
        commentaire!!.setTextColor(Color.parseColor(codeCouleur))
    }
}