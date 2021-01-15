@file:Suppress("DEPRECATION")

package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.os.AsyncTask
import android.view.View
import android.widget.Button
import java.lang.Exception
import java.sql.DriverManager

class Temperatures : AppCompatActivity() {
    var temperature: TextView? = null
    var valide: TextView? = null
    var temps: TextView? = null
    var msgErreur: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_temperature)

        //propriétés
        temperature = findViewById<View>(R.id.temperatureValeur) as TextView
        valide = findViewById<View>(R.id.temperatureValide) as TextView
        temps = findViewById<View>(R.id.temperatureTemps) as TextView
        msgErreur = findViewById<View>(R.id.temperatureValeur) as TextView
        Async().execute()
    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var temper = ""
        var tem= ""
        var vali = true
        var erreur = ""

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
                    temper = "${resultatRecup.getString("value")} "
                    tem =  "${resultatRecup.getString("time")}"
                    vali = resultatRecup.getBoolean("inRange")
                }
            } catch (e: Exception) {
                erreur = e.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            if(erreur !== "") msgErreur!!.text=erreur
            temperature!!.text = temper
            temps!!.text = tem
            if (vali == true){
                valide!!.text ="La température est idéale"
            } else {
                valide!!.text ="La température est anormalement élevée"
            }
            super.onPostExecute(aVoid)
        }
    }
}