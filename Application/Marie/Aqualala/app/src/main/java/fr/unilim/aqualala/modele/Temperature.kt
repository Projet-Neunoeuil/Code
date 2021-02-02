package fr.unilim.aqualala.modele

class Temperature constructor(temperatureValeur: Double, dateHeureMinute: String, estDansLaLimite: Boolean) {
    var temperature=temperatureValeur
    var temps=dateHeureMinute
    var valideTemperature=estDansLaLimite

    //Afficher l'initialisation
    init {
        println("Température = $temperature")
        println("Temps = $temps")
        println("validité de température = $valideTemperature")
    }

    //retourner la validité de la température
    fun validiteTemperatureText(): String{
        if (this.valideTemperature) return "La température est idéale"
        return "La température est anormalement élevée"
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
        if (this.valideTemperature) return "#B6DF5D"
        return "#E2685F"
    }
}