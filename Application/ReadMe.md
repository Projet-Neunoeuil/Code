# Application

## Syntaxe
Voici un programme utilisant un maximum de fonction
```
Programme Main
Début
  Avec x, y, z, compteur, iterateur : Entier
       fonction augmenter_x(x : Entier) retourne Entier

  compteur <- 0
  iterateur <- 0

  Afficher "Saisir x, y et z : "
  Saisir x
  Saisir y
  Saisir z

  Si x < 5 Alors
    x <- x + 5
  Sinon
    Tant que x > 5 Faire
      x <- x - 1
    FinFaire
  FinSi

  Pour iterateur de 0 à 6 Faire
    x <- x + y - z
    y <- y + 2 * iterateur
    z <- z / (iterateur + 1) //<- ceci doit être un entier
  FinFaire
  
  Afficher augmenter(x)
Fin Main
```
et la fonction augmenter_x de cet algorithme :
```
Fonction augmenter_x(x : Entier) retourne Entier
Début
  Retourne x + 1
Fin augmenter_x
```

Et voici le code kottlin correspondant :
```kotlin
    var x:Int
    var y:Int
    var z:Int
    val compteur:Int
    compteur = 0
    println("Veuillez saisir x : ")
    x = readLine()!!.toInt()
    println("Veuillez saisir y : ")
    y = readLine()!!.toInt()
    println("Veuillez saisir z : ")
    z = readLine()!!.toInt()
    if (x < 5)
    {
      x = x - 5
    }
    else
    {
      while (x > 5)
      {
        x = x - 1
      }
    }
    for (i in 0..6)
    {
      x = x + y - z
      y = y + 2 * i
      z = (z / (i + 1)).toInt()
    }
    println(augmenterX(x))
```
et la fonction :
```kotlin
fun augmenterX(x:Int):Int {
    return x + 1
  }
```
Maintenant expliquons :

A noter : La différence est faites entre `var`et `val`, le premier peut changer et le second ne bouge pas, un peu a la manière du `final` en java. Il n'y a pas non plus de point-virgule.

`Avec x : Entier` devient `var x:Int` en Kotlin.

`compteur <- 0` devient `compteur = 0` en kotlin.

`Saisir y` devient `y = readLine()!!.toInt()` en Kotlin. Pour comprendre cette instruction il faut la décomposer :
- `readLine()` permet de lire l'entrée standard (la console).
- `!!` permet d'emettre l'assertion que readLine() est non-null.
- `toInt()` permet de transformer la valeur obtenue par readLine() en entier.

`Si ... Alors ... Sinon ... FinSi` devient `if (...) { ... } else: { ... }` en kotlin.

`TantQue ... Faire ... FinFaire` devient `while ( ... ) { ... }` en kotlin.

`Pour ... de X à Y Faire ... FinFaire` devient `for ( ... in X..Y ) { ... }` en kotlin.

`Afficher augmenter_x(x)` devient `println(augmenterX(x))` en kotlin.

`fonction nomFonction(nomVar : TypeVar) retourne TypeVar2 Début` devient `fun nomFonction(nowVar;TypeVar):TypeVar2` en kotlin.

`Retourne` devient `return` en kotlin.

___

## Convention
Les conventions utilisées serons celles du java :
- variables en camelCase
- constante en SNAKE_CASE
- Classes en CamelCase

la pluspart des autres conventions sont de toute façon gérées par l'IDE quel qu'il soit.
___
## Apparence
En suivant la charte graphique, voici l'aparence de l'application souhaitée :

![Appli-preview](https://github.com/Projet-Neunoeuil/Code/blob/main/Application/appli-preview.png?raw=true)
