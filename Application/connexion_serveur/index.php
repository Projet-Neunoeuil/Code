<?php 
require_once("include/config.inc.php");
require_once("include/autoLoad.inc.php");

$connexion = new Mypdo();

// contrôle de réception de paramètre
if(isset($_REQUEST['operation'])) {
	
	// demande de récupération des infos sur Android
	// $_REQUEST contient le contenu des variables $_GET,$_POST,$_COOKIE
	if($_REQUEST['operation']=='récupération') {
		
		try {
			print('récupération%');
			
			$req = $connexion->prepare('SELECT FROM');
			$req->execute();
			
			// récupération du premier
			if($ligne = $req->fetch(PDO::FETCH_ASSOC)){
				// tableau transformé en format json 
				print(json_encode($ligne));
			}
		}catch(PDOException $e) {
			print 'Erreur !'.$e->getMessage();
			die();
		}
		
	// enregistrement des infos dans la BD
	}elseif($_REQUEST['operation']=='enregistrement') {
		
		try {
			// récupération des données en request
			$donneesAndroid = $_REQUEST['donneesAndroid'];
			$donneesDecodees = json_decode($donneesAndroid);
			
			/**
			* $attribut = $donnesDecodees[i]
			*/
			
			// insertion dans la BD
			print('enregistrement%');
			$insertReq = 'INSERT INTO ()';
			$insertReq .= 'VALUES (:)';
			print($insertReq);
			$req = $connexion->prepare($insertReq);
			$insertReq->bindValue();
			$req->execute();
		}catch(PDOException $e) {
			print 'Erreur !'.$e->getMessage();
			die();
		}
		
	}
}
?>