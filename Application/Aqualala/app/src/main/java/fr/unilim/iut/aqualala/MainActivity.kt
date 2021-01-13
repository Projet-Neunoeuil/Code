@file:Suppress("DEPRECATION")

package fr.unilim.iut.aqualala

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.Button
import java.lang.Exception
import java.sql.DriverManager

class MainActivity : AppCompatActivity() {
    var texte: TextView? = null
    var msgErreur: TextView? = null
    var show: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //propriétés
        texte = findViewById<View>(R.id.textView) as TextView
        msgErreur = findViewById<View>(R.id.textView2) as TextView
        show = findViewById<View>(R.id.button) as Button
        show!!.setOnClickListener { Async().execute() }
    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var donnees = ""
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
                val resultSet = etat.executeQuery("SELECT * FROM Temperature")
                while (resultSet.next()) {
                    donnees += "${resultSet.getString("value")} ${resultSet.getString("time")} ${resultSet.getBoolean("inRange")} \n"
                }
            } catch (e: Exception) {
                erreur = e.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            texte!!.text = donnees
            if (erreur !== "") msgErreur!!.text = erreur
            super.onPostExecute(aVoid)
        }
    }
}