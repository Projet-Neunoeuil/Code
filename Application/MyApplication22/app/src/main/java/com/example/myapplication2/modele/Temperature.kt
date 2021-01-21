package com.example.myapplication2.modele

class Temperature constructor(temperature: Double, temps: String, estDansLaLimite: Boolean) {
    var valideTemperature=estDansLaLimite
    init {
        println("Initialiser")
    }

    fun validite_temperature(): String{
        if (this.valideTemperature)

    }

}