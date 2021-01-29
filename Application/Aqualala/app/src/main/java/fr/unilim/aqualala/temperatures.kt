@file:Suppress("DEPRECATION")

package fr.unilim.aqualala

import fr.unilim.aqualala.modele.Temperature
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import java.lang.Exception
import java.sql.DriverManager
import java.sql.SQLException

class Temperatures : AppCompatActivity() {
    var temperatureView: TextView? = null
    var valideView: TextView? = null
    var tempsView: TextView? = null
    var msgErreurView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_temperature)

        //Initialiser les views
        initView()
        //Utiliser handler pour rafraichir
        val handlder = Handler(Looper.getMainLooper())
        handlder.post(object:Runnable{
            override fun run() {
                Async().execute()
                handlder.postDelayed(this,5000)
            }
        })
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
            else {
            //Utiliser sans BD
                temperature.temperature=27.0
                temperature.temps="2020-10-05 17:22:33"
                temperatureView!!.text = temperature.temperature.toString() + " °C"
                tempsView!!.text = temperature.dateTempsChangement()
                valideView!!.text = temperature.validite_temperature()
                valideView!!.setTextColor(getResources().getColor(R.color.Erreur))
            }
            super.onPostExecute(aVoid)
        }
    }
}