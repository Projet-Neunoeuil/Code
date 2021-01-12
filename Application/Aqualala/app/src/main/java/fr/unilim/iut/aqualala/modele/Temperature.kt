package fr.unilim.iut.aqualala.modele

import org.json.JSONArray
import java.time.LocalDateTime

class Temperature {
    // constantes
    private val minLimite: Double = 22.0
    private val maxLimite: Double = 28.0

    // propriétés
    var valeur: Double
    var dateTime: String
    var estDansLaLimite: Boolean = true


    constructor(valeur: Double, dateTime: String) {
        this.valeur = valeur
        this.dateTime = dateTime
        this.estDansLaLimite(valeur)
    }

    private fun estDansLaLimite(valeur: Double) {
        this.estDansLaLimite = (valeur >= minLimite || valeur <= maxLimite)
    }

    /**
     * conversion des données de la température en format JSONArray
     * @return
     */
    fun convertToJSONArray(): JSONArray {
        val liste = arrayListOf<Any>()
        liste.add(valeur)
        liste.add(dateTime)
        liste.add(estDansLaLimite)
        return JSONArray(liste)
    }
}