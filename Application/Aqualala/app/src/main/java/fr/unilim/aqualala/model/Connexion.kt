package fr.unilim.aqualala.model

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class Connexion constructor(){
    fun connectionBD() : Statement{
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val connexion = DriverManager.getConnection(
            //jdbc:mysql://<IP>:<port>/<nom de la base>
            "jdbc:mysql://${Constantes.IP}:${Constantes.PORT}/${Constantes.NOM_BASE_DONNEES}",
            Constantes.USER,
            Constantes.PASSWORD
        )
        return connexion.createStatement()
    }

    fun recupererDonnees(requete: String): ResultSet{
        return connectionBD().executeQuery(requete)
    }
}