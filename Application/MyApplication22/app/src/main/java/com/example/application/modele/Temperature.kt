package com.example.application.modele

class Temperature {
    // propriétés
    var valeur: Double
    var temps: String
    var estDansLaLimite: Boolean


    constructor(valeur: Double, dateTime: String, estDansLaLimite: Boolean) {
        this.valeur = valeur
        this.temps = dateTime
        this.estDansLaLimite = estDansLaLimite
    }
}