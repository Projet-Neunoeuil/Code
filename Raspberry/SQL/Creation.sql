/*
  Suppréssion des tables si elles existent pour purger la BD
*/
DROP TABLE IF EXISTS `Temperature`;
DROP TABLE IF EXISTS `Parametters`;
DROP TABLE IF EXISTS `DailyAverage`;
DROP TABLE IF EXISTS `MonthlyAverage`;
DROP TABLE IF EXISTS `Month`;


/*
  Table température : enregistre la température, la date et heure d'enregistrement et si oui ou non la température est valide
*/
CREATE TABLE `Temperature` (
`value` DOUBLE (5,2) NOT NULL,
`time` DATETIME NOT NULL,
`inRange` BOOLEAN NOT NULL);

/*
  Table paramètres : enregistre les choix de l'utilisateur, non modifiable lors des 1ères realease, editable via l'appli a posteriori
  Sauvegardée sur 24h
*/
CREATE TABLE `Parameters` (
`minTemp` DOUBLE (5,2) NOT NULL,
`maxTemp` DOUBLE (5,2) NOT NULL,
`blueTime` TIME NOT NULL,
`whiteTime` TIME NOT NULL);

/*
  Moyenne journalière
  Sauvegardée sur 30 jours
*/
CREATE TABLE `DailyAverage` (
`value` DOUBLE (5,2) NOT NULL,
`day` DATE NOT NULL);

/*
  Moyenne par mois
  Sauvegardé sur 12 mois
*/
CREATE TABLE `MonthlyAverage` (
`value` DOUBLE (5,2) NOT NULL,
`month` SMALLINT (2) NOT NULL);

/*
  Association Numéro de mois, nom du mois (1 = janvier)
*/
CREATE TABLE `Month` (
`monthNumber` SMALLINT (2) PRIMARY KEY NOT NULL,
`monthName` VARCHAR (9) NOT NULL);

/*
  Clé étrangère sur MonthlyAverage pour le mois (vérifie que le mois est valide)
*/
ALTER TABLE `MonthlyAverage` ADD CONSTRAINT `MonthlyAverage_month-Month_monthNumber` FOREIGN KEY (`month`) REFERENCES `Month`(`monthNumber`);
