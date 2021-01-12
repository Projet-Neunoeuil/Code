package fr.unilim.iut.aqualala.modele

import android.util.Log
import fr.unilim.iut.aqualala.outils.AccesHTTP
import fr.unilim.iut.aqualala.outils.AsyncResponse
import org.json.JSONArray

class AccesDistant : AsyncResponse {
    // constante
    private val serveraddr: String = "http://10.181.226.33/Projet Neunoeil/Code/Application/connexion_serveur/index.php"

    /**
     * retour du serveur distant
     * @param output
     */
    override fun processFinish(output: String) {
        // voir concrètement les erreurs qui viennent du serveur (PHP)
        Log.d("serveur", "*********************$output")

        // découpage du message reçu avec %
        val message: Array<String> = output.split("%").toTypedArray()
        // valeurs $_REQUEST dans index.php
        // contenu message[0] : soit "enregistrement" soit "récupération" soit "Erreur !"
        // contenu message[1] : le reste du message

        // s'il y a bien 2 informations dans le message
        if (message.size > 1) {
            when (message[0]) {
                "enregistrement" -> Log.d("enregistrement","*************" + message[1])
                "récupération" -> Log.d("récupération","*************" + message[1])
                else -> Log.d("Erreur !","*************" + message[1])
            }
        }
    }

    // paramètre operation envoie une demande de récupération "récupération"
    // paramètre operation envoie des données Android au serveur "enregistrement"
    fun envoi(operation: String, donneesJSON: JSONArray) {
        val accesDonnees: AccesHTTP = AccesHTTP()

        // lien de délégation
        // au retour du asynchrone du serveur, processFinish() de accès distant va être exécutée
        accesDonnees.delegate = this

        // ajout paramètres
        accesDonnees.addParam("operation", operation)
        accesDonnees.addParam("donneesAndroid", donneesJSON.toString())

        // appel au serveur
        accesDonnees.execute(serveraddr)
    }

}