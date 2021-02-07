package fr.unilim.aqualala.controller

import android.util.Log
import fr.unilim.aqualala.model.Constantes

class Temperature constructor(temperatureValeur: Double, dateHeureMinute: String, estDansLaLimite: Boolean) {
    var temperature=temperatureValeur
    var temps=dateHeureMinute
    var valideTemperature=estDansLaLimite

    //afficher l'initialisation
    init {
        Log.d("TEMPERATURE", "Température = $temperature\n" +
                "Temps = $temps\n" +
                "Validité de température = $valideTemperature")
    }

    //retourner la validité de la température
    fun validiteTemperatureText(): String{
        if (this.temperature < Constantes.MIN_TEMP) return "La température est anormalement basse"
        else if (this.temperature > Constantes.MAX_TEMP) return "La température est anormalement élevée"
        else return "La température est idéale"
    }

    //récupérer l'heure et les minutes ("hh:mm") de "aaaa-mm-jj hh:mm:ss"
    fun dateTempsChangement() : String{
        val listeDateHeure=temps.split(" ")
        val stringHeureMinute=listeDateHeure[1]
        val listeHeureMinute=stringHeureMinute.split(":")
        return listeHeureMinute[0]+":"+listeHeureMinute[1]
    }

    //changer la couleur de texte selon la validité de  la température
    fun couleurChangement(): String{
        if (this.valideTemperature) return Constantes.VERT
        return Constantes.ROUGE
    }
}