package fr.unilim.aqualala.modele

import java.sql.ResultSet

class Temperature constructor(temperatureValeur: Double, dateHeureMinute: String, estDansLaLimite: Boolean) {
    var temperature=temperatureValeur
    var temps= dateHeureMinute
    var valideTemperature=estDansLaLimite

    //Afficher l'inistialiser
    init {
        println("Température = $temperature")
        println("Temps = $temps")
        println("validité de température = $valideTemperature")
    }

    //retourer de la validité de la température
    fun validiteTemperatureText(): String{
        if (this.valideTemperature) return "La température est idéale"
        else if (this.temperature<Constantes.MINTEMPERATURE)  return "La température est anormalement basse"
        return "La température est anormalement élevée"
    }

    //changer "aaaa-mm-jj hh:mm:ss" en "hh:mm"
    fun dateTempsChangement() : String{
        val listeDateHeure=temps.split(" ")
        val stringHeureMinute=listeDateHeure[1]
        val listeHeureMinute=stringHeureMinute.split(":")
        return listeHeureMinute[0]+":"+listeHeureMinute[1]
    }
    //récupérer des données de table rendue
    fun recupereDonne(resultatRecup: ResultSet){
        if (resultatRecup.next()) {
            temperature = resultatRecup.getDouble("value")
            temps = "${resultatRecup.getString("time")}"
            valideTemperature = resultatRecup.getBoolean("inRange")
        }
    }
    //changer la couleur de text selon la validité de  la température
    fun couleurChangement(): String{
        if (this.valideTemperature) return "#B6DF5D" //vert
        return "#E2685F" //rouge
    }
}