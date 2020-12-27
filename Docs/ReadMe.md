# Docs

## Arduino
Pour les programmes écrits sur arduino, il ne sera pas complexe d'écrire une documentation à partir des simples commentaires ajoutés, il n'est pas nécessaire d'avoir de syntaxe particulière, il suffit de penser à commenter son code.

___
## Python
Afin de faciliter l'écriture de la documentation, on utilisera le génératuer Sphinx , son installation est décrite [ici](https://www.sphinx-doc.org/en/master/usage/quickstart.html) afin de le faie marcher nous utiliserons la syntaxe épytext plus proche de la JavaDoc et de la KDoc décrite [ici](http://epydoc.sourceforge.net/epytext.html) voici un exemple tiré de ce site légerement remanié :
```python
def example(x):
    """
    Fonction déterminant l'entier suivant X.
    @param x: L'entier pour lequel on
              on veut obtenir le suivant
    @type x: entier
    @return: l'entier suivant directement X
    """
    return x + 1
```

___
## Kotlin
Nous utiliserons la syntaxe KDoc, très proche de la javaDoc.
voici un exemple de syntaxe en code :
```kotlin
/**
* Cette fonction traite un nombre X pour renvoyer son deuxième succésseur entier.
* @param x le nombre à traiter
* @return Le deuxième succeceur dans les entiers.
*/
maFonction(x:Int):Int {
  return x + 2
}
```
Je mettrais en gras les definitions officielle que je n'ai pas tout à fait comprise
Les différents paramètres de KDoc sont :
- `@param <name>` ou `@param[name]` permet de documenter l'utilité (et accessoirement d'indiquer) des paramètres
- `@return` pour indiquer que l'on parles de la valeur de retour
- `@constructor` pour indiquer que l'on parles du constructeur
- `@reciever` **Documente le receveur d'une fonction d'extension**
- `@property <name>` **Documente la propriété d'une classe qui porte le nom indiqué. Cette balise peut être utilisée pour documenter les propriétés déclarées dans le constructeur primaire, où il serait gênant de placer un commentaire doc directement avant la définition de la propriété.**
- `@throws <class>`ou `@exception <class>` Indique quelle classe d'erreur peut être renvoyée
- `@sample <identifier>` **Intégrer le corps de la fonction avec le nom qualifié spécifié dans la documentation de l'élément actuel, afin de montrer un exemple de la manière dont l'élément pourrait être utilisé.**
- `@see <identifier>` : Ajoute un lien vers la classe spécifié dans la parti "voir aussi" de la documentation
- `@author`spécifie l'auteur du code documenté
- `@since` Spécifie la version de l'app (ex : 5.2.9) dans laquelle cette fonction a été implémentée
- `@supress` permet de retirer cet élément de la doc générée 

Note : KDoc ne supporte pas le paramètre `@deprecated`, lui préferer l'annotation `@Deprecated` directement en cod.
Ex :
```kotlin
/**
* @supress
*/
@Deprecated
fun maFonction(x:Int):Int {
  var y: Int
  y = x
  y = y + 2
  return y
}

// code

/**
* Cette fonction traite un nombre X pour renvoyer son deuxième succésseur entier.
* @param x le nombre à traiter
* @return Le deuxième succeceur dans les entiers.
*/
fun maNouvelleFonction(x:Int):Int {
  return x + 2
}
```

Afin de générer les sources, il suffit de suivres les instructions du [ReadMe.md de Dokka](https://github.com/Kotlin/dokka/blob/master/README.md)
