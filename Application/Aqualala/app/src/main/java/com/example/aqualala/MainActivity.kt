@file:Suppress("DEPRECATION")

package com.example.aqualala

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import java.sql.DriverManager

class MainActivity : AppCompatActivity() {
    var resulatAfficher: TextView? = null
    var boutonAfficher: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resulatAfficher = findViewById<View>(R.id.affichage) as TextView
        boutonAfficher = findViewById<View>(R.id.bouton) as Button
        boutonAfficher!!.setOnClickListener { Async().execute() }
    }

    internal inner class Async: AsyncTask<Void?, Void?, Void?>(){
        //Résulat envoyé
        var renvoi = ""

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
                //Connection avec MySQL avec url,user, mode de pass
                var cn = DriverManager.getConnection(
                    //jdbc:mysql://<IP ou nom de domaine>:<port>/<nom de BD>
                    "jdbc:mysql://193.26.21.39:3306/Application",
                    "Appli","#M0td3p@553"
                )
                //
                var stmt = cn.createStatement()//Réaliser Statement pour exécuter le requete SQL
                var resultat = stmt.executeQuery("SELECT * FROM Temperature")//Recevoir le résultattat
                if (resultat.next()) {
                    renvoi += resultat.getString("value")+" "+resultat.getString("time")+" "+resultat.getBoolean("inRange")
                }
            } catch (ex: Exception) {
                renvoi = ex.toString()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            resulatAfficher!!.text= renvoi
            super.onPostExecute(aVoid)
        }
    }
}