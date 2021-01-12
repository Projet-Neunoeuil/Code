package fr.unilim.iut.aqualala.outils

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.os.AsyncTask;

/**
 * Classe technique de connexion distante HTTP
 */
class AccesHTTP : AsyncTask<String, Integer, Long>() {

    // propriétés
    var ret: String ="" // information retournée par le serveur
    var delegate: AsyncResponse? = null // gestion du retour asynchrone
    private var parametres: String = "" // paramètres à envoyer en POST au serveur

    /**
     * Construction de la chaîne de paramètres à envoyer en POST au serveur
     * @param nom
     * @param valeur
     */
    fun addParam(nom: String, valeur: String) {
        try {
            if (parametres == "") {
                // premier paramètre
                parametres = URLEncoder.encode(nom, "UTF-8") + "=" + URLEncoder.encode(valeur, "UTF-8")
            }else{
                // paramètres suivants (séparés par &)
                parametres += "&" + URLEncoder.encode(nom, "UTF-8") + "=" + URLEncoder.encode(valeur, "UTF-8")
            }
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée par la méthode execute
     * permet d'envoyer au serveur une liste de paramètres en GET
     * @param params contient l'adresse du serveur dans la case 0 de urls
     * @return null
     */
    override fun doInBackground(vararg params: String?): Long? {

        // pour éliminer certaines erreurs
        System.setProperty("http.keepAlive", "false")
        // objets pour gérer la connexion, la lecture et l'écriture
        var urls: String = params as String
        var writer: PrintWriter? = null
        var reader: BufferedReader? = null
        var connexion: HttpURLConnection? = null

        try {
            // création de l'url à partir de l'adresse reçu en paramètre, dans urls[0]
            var url: URL = URL(urls[0].toString())
            // ouverture de la connexion
            connexion = url.openConnection() as HttpURLConnection
            connexion.doOutput = true
            // choix de la méthode POST pour l'envoi des paramètres
            connexion.requestMethod = "POST"
            connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            connexion.setFixedLengthStreamingMode(parametres.toByteArray().size)
            // création de la requete d'envoi sur la connexion, avec les paramètres
            writer = PrintWriter(connexion.outputStream)
            writer.print(parametres)
            // Une fois l'envoi réalisé, vide le canal d'envoi
            writer.flush()
            // Récupération du retour du serveur
            reader = BufferedReader(InputStreamReader(connexion.inputStream))
            ret = reader.readLine()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            // fermeture des canaux d'envoi et de réception
            try{
                writer!!.close()
            }catch(e: Exception){}
            try{
                reader!!.close()
            }catch(e: Exception){}
        }
        return null
    }

    /**
     * Sur le retour du serveur, envoi l'information retournée à processFinish
     * @param result
     */
    override fun onPostExecute(result: Long) {
        // ret contient l'information récupérée du serveur
        delegate!!.processFinish(ret.toString())
    }

}