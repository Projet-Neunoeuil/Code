package com.example.application

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.application.modele.Temperature
import java.sql.DriverManager
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    var temperatureView: TextView? = null
    var valideView: TextView? = null
    var tempsView: TextView? = null
    var msgErreurView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //propriétés
        temperatureView = findViewById<View>(R.id.temperatureValeur) as TextView
        valideView = findViewById<View>(R.id.temperatureValide) as TextView
        tempsView = findViewById<View>(R.id.temperatureTemps) as TextView
        msgErreurView = findViewById<View>(R.id.temperatureValeur) as TextView
        Async().execute()
    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var temperature = Temperature(0.0, "", true)
        var erreurDesDonnees = ""
        var dateFormat = "hh:mm a"

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance()
                val connexion = DriverManager.getConnection(
                    //jdbc:mysql://<IP>:<port>/<nom de la base>
                    "jdbc:mysql://193.26.21.39:3306/Application",
                    "Appli",
                    "#M0td3p@553"
                )
                val etat = connexion.createStatement()

                //récupération de données
                val resultatRecup = etat.executeQuery("SELECT * FROM Temperature")
                if (resultatRecup.next()) {
                    temperature.valeur = resultatRecup.getString("value").toDouble()
                    //val tempsAFormatter = resultatRecup.getString("time")
                    //temperature.temps =
                    // récupérer seulement heure et minutes
                    temperature.estDansLaLimite = resultatRecup.getBoolean("inRange")
                }
            } catch (e: Exception) {
                erreurDesDonnees = e.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            if(erreurDesDonnees !== "") {
                msgErreurView!!.text = erreurDesDonnees
            } else {
                temperatureView!!.text = "${temperature.valeur} °c"
                tempsView!!.text = temperature.temps
                if (temperature.estDansLaLimite){
                    valideView!!.text ="La température est idéale"
                } else {
                    valideView!!.text ="La température est anormalement élevée"
                }
            }
            super.onPostExecute(aVoid)
        }
    }
}