package com.example.myapplication2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.lang.Exception
import java.sql.DriverManager

class MainActivity : AppCompatActivity() {
    var temperatureView: TextView? = null
    var valideView: TextView? = null
    var tempsView: TextView? = null
    var msgErreurView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        Async().execute()
    }

    //Initialiser les views
    private fun initView(){
        //propriétés
        temperatureView = findViewById<View>(R.id.temperatureValeur) as TextView
        valideView = findViewById<View>(R.id.temperatureValide) as TextView
        tempsView = findViewById<View>(R.id.temperatureTemps) as TextView
        msgErreurView = findViewById<View>(R.id.temperatureValeur) as TextView

    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var temper = ""
        var tem= ""
        var vali = true
        var erreurDesDonnees = ""

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
                erreurDesDonnees = e.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            if(erreurDesDonnees !== "") msgErreurView!!.text=erreurDesDonnees
            temperatureView!!.text = temper+" °C"
            tempsView!!.text = tem
           if (vali == true){
                valideView!!.text ="La température est idéale"
            } else {
                valideView!!.text ="La température est anormalement élevée"
            }
            super.onPostExecute(aVoid)
        }
    }
}