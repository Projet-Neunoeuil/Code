package fr.unilim.aqualala.modele

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class ConnecteurBD  constructor(nomColone: String, nomTable: String){
    var colone = nomColone
    var table = nomTable
    var condition= ""

    //constructeur avec condition
    constructor(nomColone: String, nomTable: String,conditionSelection:String) : this(nomColone,nomTable) {
        condition= conditionSelection
    }

    //afficher l'inistialiser
    init {
        println("colone = $colone")
        println("Table = $table")
        println("condition = $condition")
        var requete = "SELECT $colone FROM $table"
        if (condition != "") {
            requete +=" WHERE $condition"
        }
        println(requete)
    }

    fun connectionBD() : Statement{ //avec url, user, mdp en entrée
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val connexion = DriverManager.getConnection(
            //jdbc:mysql://<IP>:<port>/<nom de la base>
            "jdbc:mysql://${Constantes.IP}:${Constantes.PORT}/${Constantes.NOM_BASE_DONNEES}",
            Constantes.USER,
            Constantes.PASSWORD
        )
        return connexion.createStatement()
    }

    fun selectionBD():ResultSet{
        val etat = connectionBD()
        var requete = "SELECT $colone FROM $table"
        //récupération de données
        if (condition != "") {
            requete +=" WHERE $condition"
        }
        return etat.executeQuery(requete)
    }

}