package com.example.myapplication2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    var numeroView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        /*
        //Variable pour tester rafraichaire par 5 secondes
        var progress = 0
        numeroView!!.text=progress.toString()*/

        //Utiliser handler pour rafraichir
        val handlder = Handler(Looper.getMainLooper())
        handlder.post(object:Runnable{
            override fun run() {
                /*
                //Variable pour tester rafraichaire par 5 secondes
                progress++
                numeroView!!.text=progress.toString()*/
                Async().execute()
                handlder.postDelayed(this,5000)
            }

            //Ajoutera un boutton pour arreter ce thread sinon surchargé
            /*nom_boutton_arreter.setOnClickListener{
                handlder.removeCallbacks(runnable)
            }*/
        })
    }

    //Initialiser les views
    private fun initView(){
        //propriétés
        temperatureView = findViewById<View>(R.id.temperatureValeur) as TextView
        valideView = findViewById<View>(R.id.temperatureValide) as TextView
        tempsView = findViewById<View>(R.id.temperatureTemps) as TextView
        msgErreurView = findViewById<View>(R.id.erreurView) as TextView
        numeroView = findViewById<View>(R.id.numeroView) as TextView

    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var temperature = Temperature(0.0,"",true)
        var erreurDesDonnees = ""
        //var progress=0

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
                        temperature.temperature = resultatRecup.getDouble("value")
                        temperature.temps =  "${resultatRecup.getString("time")}"
                        temperature.valideTemperature = resultatRecup.getBoolean("inRange")
                    }
                } catch (e: Exception) {
                    erreurDesDonnees = e.toString()
                } catch(e:SQLException) {
                    erreurDesDonnees = e.toString()
                }
            //}
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            if(erreurDesDonnees !== "") msgErreurView!!.text=erreurDesDonnees
            else {
                //Utiliser sans BD
                temperatureView!!.text = temperature.temperature.toString() + " °C"
                tempsView!!.text = temperature.dateTempsChangement()
                valideView!!.text = temperature.validite_temperature()
            }
            super.onPostExecute(aVoid)
        }
    }
}