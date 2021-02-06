package fr.unilim.aqualala.modele

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class ConnecteurBD  constructor(select: String, from: String){
    var colone = select
    var table = from
    var condition= ""
    var order= ""

    //constructeur avec condition
    constructor(select: String, from: String,where:String) : this(select,from) {
        condition= where
    }

    constructor(select: String, from: String,where:String,orderBy:String) : this(select,from,where) {
        order= orderBy
    }
    //afficher l'inistialiser
    init {
        var requete = "SELECT $colone FROM $table"
        if (condition != "") requete +=" WHERE $condition"
        println(requete)
    }

    fun connectionBD() : Statement{ //avec url, user, mdp en entrée
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val connexion = DriverManager.getConnection(
            //jdbc:mysql://<IP>:<port>/<nom de la base>
            "jdbc:mysql://${Constantes.IP}:${Constantes.PORT}/${Constantes.NOM_BASE_DONNEES}",
            Constantes.USER,
            Constantes.PASSEWORD
        )
        return connexion.createStatement()
    }

    fun selectionBD():ResultSet{
        val etat = connectionBD()
        var requete = "SELECT $colone FROM $table"
        //récupération de données
        if (condition != "") requete +=" WHERE $condition"
        if (order != "") requete +=" ORDER BY $order"
        return etat.executeQuery(requete)
    }

}