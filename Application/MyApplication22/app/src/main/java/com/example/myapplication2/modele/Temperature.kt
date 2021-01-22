package com.example.myapplication2.modele

class Temperature constructor(temperatureValeur: Double, heureMinute: String, estDansLaLimite: Boolean) {
    var temperature=temperatureValeur
    var temps= heureMinute
    var valideTemperature=estDansLaLimite


    init {
        println("Initialiser")
    }

    fun validite_temperature(): String{
        if (this.valideTemperature) return "La température est idéale"
        return "La température est anormalement élevée"
    }

}