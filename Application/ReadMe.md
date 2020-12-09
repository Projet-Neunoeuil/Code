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
et la fonction augmenter_x de cte algorithme :
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

Maitenons décomposons :
`s`
