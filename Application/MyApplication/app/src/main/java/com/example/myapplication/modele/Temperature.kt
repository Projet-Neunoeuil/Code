package com.example.myapplication.modele

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

    fun couleurChangement(): String{
        if (this.valideTemperature) return "#B6DF5D"
        return "#E2685F"
    }


}