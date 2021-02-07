@file:Suppress("DEPRECATION")

package fr.unilim.aqualala.view.temperature

import android.annotation.SuppressLint
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
import com.example.aqualala.R
import fr.unilim.aqualala.model.Connexion
import fr.unilim.aqualala.controller.Temperature
import fr.unilim.aqualala.model.Constantes
import java.sql.SQLException

class GalleryFragment : Fragment() {
    var temperatureView: TextView? = null
    var valideView: TextView? = null
    var tempsView: TextView? = null
    var msgErreurView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_temperature, container, false)
        val textView: TextView = view.findViewById(R.id.textView2)

        initView(view)
        RafraichirPeriodiquement(Constantes.TIME_SECOND_REFRESH)//A remettre sur 20 minutes
        return view
    }

    //Initialiser les views
    private fun initView(view: View) {
        //propriétés
        temperatureView = view.findViewById<View>(R.id.temperatureValeur) as? TextView
        valideView = view.findViewById<View>(R.id.temperatureValide) as? TextView
        tempsView = view.findViewById<View>(R.id.temperatureTemps) as? TextView
        msgErreurView = view.findViewById<View>(R.id.erreurView) as? TextView
    }

    //Rafraîchir l'interface périodiquement
    private fun RafraichirPeriodiquement(periode: Long) {
        val handlder = Handler(Looper.getMainLooper())
        handlder.post(object : Runnable {
            override fun run() {
                Async().execute()
                handlder.postDelayed(this, periode as Long)
            }
            //Ajoutera un boutton pour arreter ce thread sinon surchargé
            /*nom_boutton_arreter.setOnClickListener{
                handlder.removeCallbacks(runnable)
            }*/
        })
    }


    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var bd = Connexion()
        var requete = "SELECT value, time, inRange FROM Temperature ORDER BY time DESC"
        var temperature = Temperature(0.0, "", true)
        var erreurDesDonnees = ""

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                var resultatRecup = bd.recupererDonnees(requete)
                if (resultatRecup.next()) {
                    temperature.temperature = resultatRecup.getDouble("value")
                    temperature.temps = resultatRecup.getString("time")
                    temperature.valideTemperature = resultatRecup.getBoolean("inRange")
                }
            } catch (e: Exception) {
                erreurDesDonnees = e.toString()
            } catch (e: SQLException) {
                erreurDesDonnees = e.toString()
            } catch (e: ClassNotFoundException) {
                erreurDesDonnees = e.toString()
            }
            return null
        }

        @SuppressLint("ResourceType")
        override fun onPostExecute(aVoid: Void?) {
            if (erreurDesDonnees !== "") { //afficher message erreur si faux
                msgErreurView!!.text = erreurDesDonnees
            } else { //afficher la température sinon
                temperatureView!!.text = temperature.temperature.toString() + " °C"
                tempsView!!.text = temperature.dateTempsChangement()
                valideView!!.text = temperature.validiteTemperatureText()
                //mettre à jour la couleur selon la température
                temperatureView!!.setTextColor(Color.parseColor(temperature.couleurChangement()))
                valideView!!.setTextColor(Color.parseColor(temperature.couleurChangement()))
                //valideView!!.setTextColor(ColorStateList.createFromXml(getResources(),getResources().getXml(R.color.vert))) normalement il faut utiliser ça, mais ça marche pas donc j'utilise une autre méthode
            }
            super.onPostExecute(aVoid)
        }
    }
}
