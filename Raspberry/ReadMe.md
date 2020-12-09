# Raspberry

## Python :

### Syntaxe

Voici un algorithme d'exemple traitant un maximum d'opérations :
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

Voici le code python correspondant:
```python
x = int(input("Veuillez saisir x : "))
y = int(input("Veuillez saisir y : "))
z = int(input("Veuillez saisir z : "))
compteur = 0
iterateur = 0

if x < 5:
    x = x + 5
else:
    while x > 5:
        x = x - 1

for iterateur in range(0,6):
  x = x+y - z
  y = y + 2*iterateur
  z = int(z / (iterateur + 1))
  

print(augmenter_x(x))
```
et la fonction augmenter_x :
```python
def augmenter_x(x):
  return x + 1
```

Maintenant expliquons :

A noter : En python il n'y a ni acolades, ni point virgule.

`Avec x, y, z, compteur, iterateur : Entier` En python, une variable est instancié dès sa déclaration, donc la clause Avec est fusionné à l'instanciation.

`compteur <- 0` devient `compteur = 0` en python.

`Saisir y` devient `y = int(input("Veuillez saisir y : "))` en python. Pour comprendre cette instruction il faut la décomposer :
- `input()` permet de lire l'entrée standard (la console), il peut prendre un argument qui est le texte affiché à l'utilisateur.
- `int()` permet de récupérer la valeur entière de l'argument (dans notre cas c'est une chaine, mais ça pourrait aussi être un réel)

`Si ... Alors ... Sinon ... FinSi` devient `if ...: ... else: ...` en python.

`TantQue ... Faire ... FinFaire` devient `while ...: ...` en python.

`Pour ... de ... à ... Faire ... FinFaire` devient `for ... in range(..., ...): ...` en python.

`Afficher augmenter_x(x)` devient `print(augmenter_x(x))` en python.

`fonction nomFonction(nomVar : TypeVar) retourne TypeVar2 Début` devient `def nomFonction(nomVar):` en python.

`Retourne` devient `return` en python.

___
### Conventions :
#### Source :
Les conventions cités viennent surtout du [PEP - 0008](https://www.python.org/dev/peps/pep-0008/).
Il s'agit d'une version abrégée...trés abrégée :
#### Indentation

Utilisez 4 éspaces par niveau d'indentation

Les lignes continues doivent s'alligner soit en utilisant la jointure implicite de ligne de Python ou le `hanging indent`. Lorsque ce dernier est utiliser, il faut bien prendre en compte ce qui suit :
- Il ne doit pas y avoir d'argument sur la première ligne
- Une indentation supplémentaire doit être utilisé pour bien être compris comme ligne de continuation (8 espace au lieu de 4)

Exemple :
```python
# Correcte:

# Aligné avec le délimiteur.
foo = long_function_name(var_one, var_two,
                         var_three, var_four)

# Hanging Indent sur une fonction :
def long_function_name(
        var_one, var_two, var_three,
        var_four):
    print(var_one)

# Hanging indent sur un appel :
foo = long_function_name(
    var_one, var_two,
    var_three, var_four)
```
A noter que pour les lignes continues, l'indentation à 4 espaces est optionnelle.
```python
# Les Hanging Indents *peuvent* ne pas être indentés à 4 éspaces :
foo = long_function_name(
  var_one, var_two,
  var_three, var_four)
```

Quand la partie conditionnel d'une instruction `if` est assez longue pour devoir être écrite sur plusieurs ligne, il est important de noter que la combinaison d'un mot clé à deux caractères (`if`), d'un espace et d'une parenthèse ouvrante crée un espacement naturel de 4 éspaces pour la ligne continue suivante de la condition. Cela peut produire un conflit visuel avec la suite d'instruction indentées de l'instruction, qui sera lui aussi à 4 espaces. Le PEP ne prends pas de position explicite sur comment (ou même si l'on doit le faire) distinguer visuellement ces lignes conditionnelles du bloc d'instruction. Des options acceptables ( Attention, ce ne sont pas les seules ) sont les suivantes :
- Ne rien faire, laisser ainsi
- Ajouter un comentaire entre les conditions et les instructions
- Ajouter un niveau d'indentation supplémentaire à la ligne de conditions
```python
# Rien :
if (this_is_one_thing and
    that_is_another_thing):
    do_something()

# Commentaire
if (this_is_one_thing and
    that_is_another_thing):
    # Puisque les deux conditions sont vraies, nous pouvons continuer
    do_something()

# Indentation supplémentaire
if (this_is_one_thing 
        and that_is_another_thing): 
    do_something ()

```

Le symbole de fermeture peut soit être après le premier caractère qui n'est pas un espace (1), ou aprèsle premier caractère de ce qui a appelé la multiligne (2).
Des exemples seront plus parlant :
```python
# (1)
my_list = [
    1, 2, 3,
    4, 5, 6,
    ]
result = some_function_that_takes_arguments(
    'a', 'b', 'c',
    'd', 'e', 'f',
    )

# (2)
my_list = [
    1, 2, 3,
    4, 5, 6,
]
result = some_function_that_takes_arguments(
    'a', 'b', 'c',
    'd', 'e', 'f',
)
```

#### Tabs ou espaces?
Les espaces sont préférés aux tabs, ceux-ci ne devant être utilisé que pour du code déjà indenté avec des Tabs. Python 3 interdit le mélange des deux.

#### Taille de ligne maximale
Limiter toutes les lignes à un maximum de 79 caractères.

Pour des blocs de texte avec moins de restrictions structurelles (les commentaires par exemple), la taille d'une ligne devrait être limitée à 72 caractères.

Les barres obliques inverses peuvent parfois être appropriées. Par exemple, de longues et multiples instructions `with` ne peuvent pas utiliser de continuation implicite, donc les antislashes sont acceptables :
```python
with open('/path/to/some/file/you/want/to/read') as file_1, \
     open('/path/to/some/file/being/written', 'w') as file_2:
    file_2.write(file_1.read())
```
Un autre cas similaire peut être fait avec l'instruction `assert`.

Soyez sur d'indenter correctement les lignes continues.

#### Une ligne doit-elle s'arrêter avant ou après un opérateur binaire ?
On doit revenir a la ligne avant l'opérateur pour la lisibilité du code
```python
# il est facile de faire correspondre les opérateurs et les opérandes
income = (gross_wages
          + taxable_interest
          + (dividends - qualified_dividends)
          - ira_deduction
          - student_loan_interest)
```


#### Encodage des fichiers
Le code dans la distribution Python de base devrait toujours utiliser UTF-8 (ou ASCII dans Python 2).

Les fichiers utilisant l'ASCII (en Python 2) ou l'UTF-8 (en Python 3) ne doivent pas avoir de déclaration d'encodage.

#### Les imports
Les imports doivent être sur des lignes séparées
```python
# Correcte:
import os
import sys
```
Cependant il est accepté de faire ceci :
```python
from subprocess import Popen, PIPE
```

Les imports doivent toujours être en haut du fichier, juste après des potentiels commentaires de module et de documentation, et aussi avant les variables globales et les constantes des modules.
Les imports doivent être groupés dans l'ordre suivant :

1. Les imports de bibliothèque standard.
2. Les imports de tiers liés.
3. Les imports spécifiques à une application locale ou à une bibliothèque.

Vous devez mettre une ligne blanche entre chaque groupe d'imports.




#### Délimiteurs de chaines
En python, les simple et doubles quotes sont identiques. Ce PEP ne fait aucune recommandations pour ça.
Prenez une règle et suivez la. Cependant quand une chaine contien une simple ou une double quote, utilisez l'autre pour eviter les anti-slash dans la chaine, ça aide la lisibilité.



#### Commentaires


```python
"""
 Commentaire 1
 Commentaire ligne 2
"""
x = x + 1                # Incremente x
```

#### Conventions de nommages
Comme le dit le PEP,les conventions de nommages en python sont assez peu précise, pour simplifier ici la totalité du code untilisera le snake_case (ou SNAKE_CASE pour les constantes)

les noms de variables l (L minuscule), O (lettre o majuscule) et I (lettre i majuscule) sont à éviter car pouvant parfois etre confondue avec 0 et 1.
pour plus de details, consuleter [la section du PEP0008](https://www.python.org/dev/peps/pep-0008/#naming-conventions)


___

## Linux
Ici iront les commandes utilisées directement en console (non stockées dans des scripts .sh) afin de configurer la carte raspberry comme serveur LAMP et de le sécuriser

___
## SQL
ici iront les différentes requêtes SQL effectuées sur la BD.
