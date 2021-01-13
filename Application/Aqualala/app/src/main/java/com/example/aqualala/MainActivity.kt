package com.example.aqualala

/*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}*/


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        afficherResultat.setOnClickListener {
            Thread(Runnable {
                val sql = "SELECT * FROM Temperature;"
                mysqlConnection(sql)
            }).start()
        }
    }

    fun mysqlConnection(sql: String) {
        var cn: Connection

        try {
            Class.forName("com.mysql.jdbc.Driver")//Définir le système de démarrage de MySQL
            cn = DriverManager.getConnection(
                "jdbc:mysql://db4free.net:3306/neunoeiltest",
                "appli1", "#M0td3p@553"
            )//Etablir la connexion

            var stmt = cn.createStatement()//Réaliser Statement pour exécuter le requete SQL
            var resultSet = stmt!!.executeQuery(sql)//Recevoir le résultat
            while (resultSet.next()) {//Afficher
                Log.d(
                    "mysqlConnection: ", resultSet.getString(1) + " " +
                            resultSet.getString(2) + " " +
                            resultSet.getString(3) + " " + resultSet.getString(4)
                )
                println(resultSet.getString(1))
            }
            if (stmt != null) {
                stmt!!.close()
            }
            if (cn != null) {
                cn.close()
            }
        } catch (ex: SQLException) {
            //handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            //handle any errors
            ex.printStackTrace()
        }
    }
}