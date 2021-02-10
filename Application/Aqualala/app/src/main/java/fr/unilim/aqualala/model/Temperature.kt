package fr.unilim.aqualala.model

import android.util.Log

class Temperature constructor(valeur: Double, tempsMesure: String, estDansLaLimite: Boolean) {
    var valeur=valeur
    var tempsMesure=tempsMesure
    var estDansLaLimite=estDansLaLimite

    //afficher l'initialisation
    init {
        Log.d("TEMPERATURE",
            "Température = $valeur\n" +
                "Temps = $tempsMesure\n" +
                "Validité de température = $estDansLaLimite")
    }

    //retourner la validité de la température
    fun commentaireSurLaValiditeTemperature(): String{
        if (valeur < Constantes.MIN_TEMP && !estDansLaLimite) return "La température est anormalement basse"
        else if (valeur > Constantes.MAX_TEMP && !estDansLaLimite) return "La température est anormalement élevée"
        else return "La température est idéale"
    }

    fun recupererHeuresEtMinutes() : String{
        //aaaa-mm-jj hh:mm:ss
        val dateTemps=tempsMesure.split(" ")
        val temps=dateTemps[1]
        //hh:mm:ss
        val heureMinute=temps.split(":")
        return "Température mesurée à ${heureMinute[0]} : ${heureMinute[1]}"
    }

    //changer la couleur de texte selon la validité de  la température
    fun couleurSelonValidite(): String{
        if (estDansLaLimite) return Constantes.VERT
        return Constantes.ROUGE
    }
}