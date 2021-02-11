@file:Suppress("DEPRECATION")

package fr.unilim.aqualala.controller

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aqualala.R
import fr.unilim.aqualala.model.Connexion
import fr.unilim.aqualala.model.Temperature
import fr.unilim.aqualala.model.Constantes
import fr.unilim.aqualala.view.temperature.TemperatureView
import java.sql.SQLException

class   TemperatureController : Fragment() {
    var temperatureView = TemperatureView()
    var temperature = Temperature(0.0, "", true)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_temperature, container, false)
        temperatureView.initTextView(view)
        rafraichirPeriodiquement(Constantes.TIME_SECOND_REFRESH)//A remettre sur 20 minutes
        return view
    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var bd = Connexion()
        var requete = "SELECT value, time, inRange FROM Temperature ORDER BY time DESC"
        var erreurDesDonnees = ""

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                var resultatRecup = bd.recupererDonnees(requete)
                if (resultatRecup.next()) {
                    temperature.valeur = resultatRecup.getDouble("value")
                    temperature.tempsMesure = resultatRecup.getString("time")
                    temperature.estDansLaLimite = resultatRecup.getBoolean("inRange")
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

        override fun onPostExecute(aVoid: Void?) {
            //afficher message erreur si erreur de récupération de données
            if (erreurDesDonnees !== "") {
                temperatureView.msgErreur!!.text = erreurDesDonnees
            }
            //afficher la température sinon
            else {
                lierTemperatureAvecView(temperature)
            }
            super.onPostExecute(aVoid)
        }
    }

    //Rafraîchir l'interface périodiquement
    private fun rafraichirPeriodiquement(periode: Long) {
        val handlder = Handler(Looper.getMainLooper())
        handlder.post(object : Runnable {
            override fun run() {
                Async().execute()
                handlder.postDelayed(this, periode)
            }
            //Ajoutera un boutton pour arreter ce thread sinon surchargé
            /*nom_boutton_arreter.setOnClickListener{
                handlder.removeCallbacks(runnable)
            }*/
        })
    }

    private fun lierTemperatureAvecView(temperature: Temperature) {
        temperatureView.valeur!!.text = temperature.valeur.toString() + " °C"
        temperatureView.temps!!.text = temperature.recupererHeuresEtMinutes()
        temperatureView.commentaire!!.text = temperature.commentaireSurLaValiditeTemperature()
        temperatureView.changerCouleurTexte(temperature.couleurSelonValidite())
    }
}
