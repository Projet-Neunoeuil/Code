package fr.unilim.aqualala.model

class Constantes {
    companion object {
        // Connexion à la base de données
        val IP = "xiangyu-an.fr"
        val PORT = "3306"
        val NOM_BASE_DONNEES = "Application"
        val USER = "Appli"
        val PASSWORD = "#M0td3p@553"

        // Rafraîchissement de la récupération de données
        val TIME_SECOND_REFRESH: Long = 3000 // 3 secondes

        // Température idéale
        val MIN_TEMP = 22
        val MAX_TEMP = 28

        // Codes couleur
        val VERT = "#B6DF5D"
        val ROUGE = "#E2685F"
    }

}