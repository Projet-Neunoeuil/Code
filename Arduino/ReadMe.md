# Arduino

## Source :
Il est important de ne pas s'attribuer le travail des gens, ceci est donc tiré du site
[arduino.developpez.com](https://arduino.developpez.com/tutoriels/cours-complet-arduino/?page=bien-coder).
Ce document est écrit à titre d'uniformisation afin que les codes soit lisiblent, bien que ces règles peuvent sembler évidentes, il est toujours bon de les rappeler.

## Conventions de nommage :
Les constantes sont écrites en majuscule, et en snake_case.

Exemple : 
```c
const int CAPTEUR_TEMPERATURE = A0 //CAPTEUR_TEMPERATURE est une constante
```

Pour toutes les autres variables et fonctions (appelés aussi sous-routines) les noms seront en minuscule et en CamelCase.

Exemple :
```c
int compteurDeSignal = 0;
afficherValeurCapteur(CAPTEUR_TEMPERATURE);
```

un point virgule signifie la fin d'une instruction.

## Déclarer ses variables

Le nom des éléments type capteurs utilisés dans un projet sont généralement repris par les variables du programme, cela permet de clarifier le code et la fonction des variables.

Exemple :
```c
int brocheLed = 13; //brocheLed représente le numéro de la broche sur laquelle la LED est branchée.
```

## Indenter son code
l'indentation est importante et rends le code plus lisible, facilitant la recherche d'erreurs.

## Faire des commentaires
Il n'y a rien de plus embêtant qu'une instruction écrite dans un code et dont on a oublie l'utilité quelques jours après. Les commentaires permettent de se retrouver dans son code. Ils facilitent en outre la compréhension du code pour ceux qui en auront éventuellement besoin.

Exemples :
```c
/* 
Ce programme permet de piloter les LED à partir de certaines touches du clavier.
Principe:

Saisir les caractères dans le moniteur sériel de l'interface de programmation Arduino pour allumer et éteindre les LED.
Saisir 'R' pour allumer la LED rouge,
Saisir 'J' pour allumer la LED jaune,
Saisir 'V' pour allumer la LED verte,
Saisir 'B' pour allumer la LED bleue,
Saisir 'E' pour éteindre les LED.
*/

digitalWrite(pinLed0, HIGH); // Allumer la LED connectée à pinLed0
```

## Les fonctions (sous-routines)
Lorsque le code est important, il peut devenir obligatoire d'ajouter des sous-routines (que nous connaissons mieux sous le nom de fonctions/procédures).
Elles doivent absolument être placée **__en dessous__** de la boucle principale.

## Vérifier le code
Pour cela, sur l'IDE Arduino rien de plus simple, il y a un bouton `verify` les erreurs apparaitrons dans la console.

## AutoFormat
Pour activer/désactiver le formateur automatique il faut aller dans : `Tools > Auto Format`, ou utiliser par défaut le raccourci `... + ...`
