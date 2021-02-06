@file:Suppress("DEPRECATION")

package fr.unilim.aqualala.ui.temperature

import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import fr.unilim.aqualala.modele.ConnecteurBD
import fr.unilim.aqualala.modele.Temperature
import java.sql.SQLException

class GalleryFragment : Fragment() {
    var temperatureView: TextView? = null
    var valideView: TextView? = null
    var tempsView: TextView? = null
    var msgErreurView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_temperature, container, false)
        val textView: TextView = view.findViewById(R.id.textView2)

        liasonView(view)
        RafraichirPeriodiquement(5000)//rafraichaire par 5 secondes
        return view
    }

    //Initialiser les views
    private fun liasonView(view: View) {
        //propriétés
        temperatureView = view.findViewById<View>(R.id.temperatureValeur) as? TextView
        valideView = view.findViewById<View>(R.id.temperatureValide) as? TextView
        tempsView = view.findViewById<View>(R.id.temperatureTemps) as? TextView
        msgErreurView = view.findViewById<View>(R.id.erreurView) as? TextView
    }

    //S'il y a des erreurs mettre tous ce qui concerne la température en vide
    private fun mettreVideTemperature(){
        temperatureView!!.text = ""
        tempsView!!.text = ""
        valideView!!.text = ""
    }

    //S'il n'y a pas d'erreur, mettre tous ce qui concernent le message d'erreur en vide
    private fun mettreVideMessageErreur(){
        msgErreurView!!.text = ""
    }

    //donner les valeurs aux text view
    private fun appliqueValeurTextView(temperature: Temperature){
        temperatureView!!.text = temperature.temperature.toString() + " °C"
        tempsView!!.text = temperature.dateTempsChangement()
        valideView!!.text = temperature.validiteTemperatureText()
    }

    //changer la couleur selon la température
    private fun changerCouleurSelonValiditeTemperature(temperature: Temperature){
        temperatureView!!.setTextColor(Color.parseColor(temperature.couleurChangement()))
        valideView!!.setTextColor(Color.parseColor(temperature.couleurChangement()))
    }

    //rafrachir Ui périodiquement
    private fun RafraichirPeriodiquement(periode: Long) {
        val handlder = Handler(Looper.getMainLooper())
        handlder.post(object : Runnable {
            override fun run() {
                Async().execute()
                handlder.postDelayed(this, periode)
            }
        })
    }


    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var connectBD = ConnecteurBD("*", "Temperature","","time DESC") //connexion avec la bd
        var temperature = Temperature(0.0, "", false) //temperature
        var erreurDesDonnees = "" //message d'erreur

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val resultatRecup = connectBD.selectionBD()
                temperature.recupereDonne(resultatRecup)
            } catch (e: Exception) { erreurDesDonnees = e.toString() }
            catch (e: SQLException) { erreurDesDonnees = e.toString() }
            catch (e: ClassNotFoundException) { erreurDesDonnees = e.toString() }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            changerCouleurSelonValiditeTemperature(temperature)
            if (erreurDesDonnees !== "") { //afficher message erreur si faux
                mettreVideTemperature()
                msgErreurView!!.text = erreurDesDonnees
            } else { //afficher la température sinon
                mettreVideMessageErreur()
                appliqueValeurTextView(temperature)
            }
            super.onPostExecute(aVoid)
        }
    }
}
