package com.example.myapplication2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.myapplication2.modele.Temperature
import java.lang.Exception
import java.sql.DriverManager
import java.sql.SQLException

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
        msgErreurView = findViewById<View>(R.id.erreurView) as TextView

    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var temperature = Temperature(0.0,"",true)
        var erreurDesDonnees = ""

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance()
                val connexion = DriverManager.getConnection(
                        //jdbc:mysql://<IP>:<port>/<nom de la base>
                        "jdbc:mysql://db4free.net:3306/neunoeiltest",
                        "appli1",
                        "#M0td3p@553"
                )
                val etat = connexion.createStatement()
                //récupération de données
                val resultatRecup = etat.executeQuery("SELECT * FROM Temperature")
                if (resultatRecup.next()) {
                    temperature.temperature = resultatRecup.getDouble("value")
                    temperature.temps =  "${resultatRecup.getString("time")}"
                    temperature.valideTemperature = resultatRecup.getBoolean("inRange")
                }
            } catch (e: Exception) {
                erreurDesDonnees = e.toString()
            } catch(e:SQLException) {
                erreurDesDonnees = e.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            if(erreurDesDonnees !== "") msgErreurView!!.text=erreurDesDonnees
            //else {
                //Utiliser sans BD
                temperature.temperature=27.0
                temperature.temps="2020-10-05 17:22:33"
                temperatureView!!.text = temperature.temperature.toString() + " °C"
                tempsView!!.text = temperature.temps
                valideView!!.text = temperature.validite_temperature()
            //}
            super.onPostExecute(aVoid)
        }
    }
}