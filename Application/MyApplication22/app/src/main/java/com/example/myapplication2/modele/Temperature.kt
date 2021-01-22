package com.example.myapplication2.modele

class Temperature constructor(temperatureValeur: Double, dateHeureMinute: String, estDansLaLimite: Boolean) {
    var temperature=temperatureValeur
    var temps= dateHeureMinute
    var valideTemperature=estDansLaLimite

    init {
        println("Initialiser")
    }

    fun validite_temperature(): String{
        if (this.valideTemperature) return "La température est idéale"
        return "La température est anormalement élevée"
    }

    fun dateTempsChangement() : String{
        var listeDateHeure=temps.split(" ")
        var stringHeureMinute=listeDateHeure[1]
        var listeHeureMinute=stringHeureMinute.split(":")
        return listeHeureMinute[0]+":"+listeHeureMinute[1]
    }

}