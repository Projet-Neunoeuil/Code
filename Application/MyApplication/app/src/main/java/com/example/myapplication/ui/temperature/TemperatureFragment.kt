@file:Suppress("DEPRECATION")

package com.example.myapplication.ui.temperature

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.example.myapplication.modele.Temperature
import java.lang.Exception
import java.sql.DriverManager
import java.sql.SQLException

class GalleryFragment : Fragment() {
    var temperatureView: TextView? = null
    var valideView: TextView? = null
    var tempsView: TextView? = null
    var msgErreurView: TextView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_temperature, container, false)
        val textView: TextView = root.findViewById(R.id.textView2)

        initView(root)

        val handlder = Handler(Looper.getMainLooper())
        handlder.post(object:Runnable{
            override fun run() {
                //Variable pour tester rafraichaire par 5 secondes

                Async().execute()
                handlder.postDelayed(this,5000)

            }
            //Ajoutera un boutton pour arreter ce thread sinon surchargé
            /*nom_boutton_arreter.setOnClickListener{
                handlder.removeCallbacks(runnable)
            }*/
        })
        return root
    }
    //Initialiser les views
    private fun initView(view:View){
        //propriétés
        temperatureView = view.findViewById<View>(R.id.temperatureValeur) as? TextView
        valideView = view.findViewById<View>(R.id.temperatureValide) as? TextView
        tempsView = view.findViewById<View>(R.id.temperatureTemps) as? TextView
        msgErreurView = view.findViewById<View>(R.id.erreurView) as? TextView
    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var temperature = Temperature(0.0,"",true)
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

        @SuppressLint("ResourceType")
        override fun onPostExecute(aVoid: Void?) {
            if(erreurDesDonnees !== "") {
                msgErreurView!!.text=erreurDesDonnees
            }
            else {
                temperatureView!!.text = temperature.temperature.toString() + " °C"
                tempsView!!.text = temperature.dateTempsChangement()
                valideView!!.text = temperature.validite_temperature()
                //Changer la couleur selon la température
                temperatureView!!.setTextColor(Color.parseColor(temperature.couleurChangement()))
                valideView!!.setTextColor(Color.parseColor(temperature.couleurChangement()))

                //valideView!!.setTextColor(ColorStateList.createFromXml(getResources(),getResources().getXml(R.color.vert))) normalement il faut utiliser ça, mais ça marche pas donc j'utilise une autre méthode
            }
            super.onPostExecute(aVoid)
        }
    }
}
