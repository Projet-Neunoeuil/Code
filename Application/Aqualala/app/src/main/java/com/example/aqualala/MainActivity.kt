package com.example.aqualala

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var resultatAffiche: TextView = findViewById(R.id.renvois)
        var bouton: Button = findViewById(R.id.afficherResultat)
        bouton.setOnClickListener {

                val sql = "SELECT * FROM Temperature;"
                resultatAffiche.text=mysqlConnection(sql)

        }
    }

    fun mysqlConnection(sql: String) : String? {
        var renv: String? = ""

        try {
            Class.forName("com.mysql.cj.jdbc.Driver")//Définir le système de démarrage de MySQL
            var cn = DriverManager.getConnection(
                "jdbc:mysql://db4free.net:3306/neunoeiltest",
                "appli1", "#M0td3p@553"
            )//Etablir la connexion
            var stmt = cn.createStatement()//Réaliser Statement pour exécuter le requete SQL
            var resultat = stmt!!.executeQuery(sql)//Recevoir le résultat
            while (resultat.next()) {//Afficher
                renv += resultat.getString(1)+" "+resultat.getString(2)+" \n"
            }
            if (stmt != null) {
                stmt!!.close()
            }
            if (cn != null) {
                cn.close()
            }
        } catch (ex: SQLException) {
            //handle any errors
            return ex.toString()
        } catch (ex: Exception) {
            //handle any errors
            return ex.toString()
        }
        return renv
    }
}