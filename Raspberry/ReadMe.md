# Raspberry

## Python :

### Source :
Les convontions cités viennent surtout du [PEP - 0008](https://www.python.org/dev/peps/pep-0008/).
Il s'agit grossièrement d'une traduction.

### Indentation

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
    
    
    
# Incorrecte :

# Arguments interdits sur la première ligne s'il n'y a pas d'alignement vertical.
foo = long_function_name(var_one, var_two,
    var_three, var_four)

# Un cran d'indentation supplémentaire requis dans une fonction pour distinger le Hanging Indent des instructions.
def long_function_name(
    var_one, var_two, var_three,
    var_four):
    print(var_one)
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

### Tabs ou espaces?
Les espaces sont préférés aux tabs, ceux-ci ne devant être utilisé que pour du code déjà indenté avec des Tabs. Python 3 interdit le mélange des deux.

Les codes Python 2 mélangeant les deux doivent être convertit en utilisant uniquement des espaces.

Lorsque l'on invoque l'interpreteur de commande Python 2 en console, avec l'option -t cela renvoie des avertissement sur les codes mélangeant tab et espace. Avec -tt, ce sont des erreurs qui sont renvoyées, ces options sont grandement recommandées.

### Taille de ligne maximale
Limiter toutes les lignes à un maximum de 79 caractères.

Pour des blocs de texte avec moins de restrictions structurelles (les commentaires par exemple), la taille d'une ligne devrait être limitée à 72 caractères.

La limitation de la largeur de la fenêtre de l'éditeur permet d'avoir plusieurs fichiers ouverts côte à côte, et fonctionne bien quand on utilise des outils de révision de code qui présentent les deux versions dans des colonnes adjacentes.

L'habillage par défaut de la plupart des outils perturbe la structure visuelle du code, le rendant plus difficile à comprendre. Les limites sont choisies pour éviter l'habillage dans les éditeurs dont la largeur de fenêtre est fixée à 80, même si l'outil place un glyphe marqueur dans la dernière colonne lors de l'habillage des lignes. Certains outils Web peuvent ne pas offrir de retour à la ligne dynamique du tout.

Certaines équipes préfèrent fortement une longueur de ligne plus importante. Pour le code maintenu exclusivement ou principalement par une équipe qui peut parvenir à un accord sur cette question, il est possible d'augmenter la limite de longueur de ligne jusqu'à 99 caractères, à condition que les commentaires et les chaînes de caractères soient toujours enveloppés à 72 caractères.

La bibliothèque standard Python est conservatrice et exige de limiter les lignes à 79 caractères (et les docstrings/commentaires à 72).

La façon préférée d'envelopper les longues lignes est d'utiliser la continuation de ligne implicite de Python entre parenthèses, crochets et accolades. Les longues lignes peuvent être réparties sur plusieurs lignes en mettant les expressions entre parenthèses. Il est préférable d'utiliser ces dernières plutôt qu'une barre oblique inversée pour la continuation de la ligne.

Les barres obliques inverses peuvent parfois être appropriées. Par exemple, de longues et multiples instructions `with` ne peuvent pas utiliser de continuation implicite, donc les antislashes sont acceptables :
```python
with open('/path/to/some/file/you/want/to/read') as file_1, \
     open('/path/to/some/file/being/written', 'w') as file_2:
    file_2.write(file_1.read())
```
Un autre cas similaire peut être fait avec l'instruction `assert`.

Soyez sur d'indenter correctement les lignes continues.

### Une ligne doit-elle s'arrêter avant ou après un opérateur binaire ?
Pendant des décennies, le style recommandé était de rompre après les opérateurs binaires. Mais cela peut nuire à la lisibilité de deux façons : les opérateurs ont tendance à se disperser dans différentes colonnes de l'écran, et chaque opérateur est déplacé de son opérande vers la ligne précédente. Ici, l'œil doit faire un travail supplémentaire pour savoir quels éléments sont ajoutés et lesquels sont soustraits :
```python
# Les opérateurs sont loins de leurs opérandes
income = (gross_wages +
          taxable_interest +
          (dividends - qualified_dividends) -
          ira_deduction -
          student_loan_interest)
```
Pour résoudre ce problème de lisibilité, les mathématiciens et leurs éditeurs suivent la convention inverse. Donald Knuth explique la règle traditionnelle dans sa série "Computers and Typesetting" :"Bien que les formules à l'intérieur d'un paragraphe se brisent toujours après les opérations et les relations binaires, les formules affichées se brisent toujours avant les opérations binaires".


En suivant la tradition des mathématiques, on obtient généralement un code plus lisible :
```python
# il est facile de faire correspondre les opérateurs et les opérandes
income = (gross_wages
          + taxable_interest
          + (dividends - qualified_dividends)
          - ira_deduction
          - student_loan_interest)
```
En python les deux syntaxes sont permises, temps que la convention est localement cohérente (C'est-à-dire cohérente dans l'enssemble du projet). Cependant il est suggéré d'utiliser le style de Knuth dans le code récent

### Les lignes vièrges
Entourez les définitions des fonctions et des classes de haut niveau de deux lignes vierges.

Les définitions des méthodes à l'intérieur d'une classe sont entourées d'une seule ligne blanche.

Des lignes vierges supplémentaires peuvent être utilisées (avec modération) pour séparer des groupes de fonctions connexes. Les lignes vierges peuvent être omises entre plusieurs lignes vierges connexes (par exemple, un ensemble de mises en œuvre fictives).

Utilisez les lignes vierges dans les fonctions, avec parcimonie, pour indiquer les sections logiques.

Python accepte le caractère de flux de formulaire control-L (c'est-à-dire ^L) comme espace ; de nombreux outils traitent ces caractères comme des séparateurs de pages, vous pouvez donc les utiliser pour séparer les pages de sections connexes de votre fichier. Notez que certains éditeurs et visualiseurs de code sur le web peuvent ne pas reconnaître le control-L comme un flux de formulaire et afficheront un autre glyphe à sa place.

### Encodage des fichiers
Le code dans la distribution Python de base devrait toujours utiliser UTF-8 (ou ASCII dans Python 2).

Les fichiers utilisant l'ASCII (en Python 2) ou l'UTF-8 (en Python 3) ne doivent pas avoir de déclaration d'encodage.

Dans la bibliothèque standard, les codages non par défaut ne doivent être utilisés qu'à des fins de test ou lorsqu'un commentaire ou une docstring doit mentionner un nom d'auteur qui contient des caractères non ASCII ; sinon, l'utilisation des échappements \x, \u, \U ou \N est le moyen préféré pour inclure des données non ASCII dans les littéraux de chaîne.

Pour Python 3.0 et au-delà, la politique suivante est prescrite pour la bibliothèque standard (voir PEP 3131) : Tous les identificateurs de la bibliothèque standard Python DOIVENT utiliser des identificateurs ASCII uniquement, et DOIVENT utiliser des mots anglais lorsque cela est possible (dans de nombreux cas, des abréviations et des termes techniques sont utilisés qui ne sont pas anglais). En outre, les chaînes de caractères littérales et les commentaires doivent également être en ASCII. Les seules exceptions sont (a) les cas de tests testant les caractéristiques non ASCII, et (b) les noms des auteurs. Les auteurs dont les noms ne sont pas basés sur l'alphabet latin (latin-1, jeu de caractères ISO/IEC 8859-1) DOIVENT fournir une translittération de leurs noms dans ce jeu de caractères.

Les projets open source ayant une audience mondiale sont encouragés à adopter une politique similaire.

### Les imports
Les imports doivent généralement être sur des lignes séparées
```python
# Correcte:
import os
import sys


# Incorrecte:
import sys, os
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

Les imports absolus sont recommandés, car ils sont généralement plus lisibles et ont tendance à mieux se comporter (ou du moins à donner de meilleurs messages d'erreur) si le système d'import est mal configuré (comme lorsqu'un répertoire à l'intérieur d'un paquet se retrouve sur sys.path) :
```python
import mypkg.sibling
from mypkg import sibling
from mypkg.sibling import example
```
Toutefois, les imports relatifs explicites sont une alternative acceptable aux imports absolus, en particulier lorsqu'il s'agit de configurations complexes de package où l'utilisation d'imports absolus serait inutilement verbeuse :
```python
from . import sibling
from .sibling import example
```
Le code standard de la bibliothèque doit éviter les mises en page complexes des paquets et toujours utiliser des imports absolus.

Les imports relatifs implicites ne doivent jamais être utilisés et ont été supprimés dans Python 3.

Lorsque l'on importe une classe d'un module en contenant, il est acceptable de l'écrire ainsi :
```python
from myclass import MyClass
from foo.bar.yourclass import YourClass
```
Si cette écriture pose de problèmes de surcharge de variables locales, alors écrivez les explicitement :
```python
import myclass
import foo.bar.yourclass
```
et utiliser "myclass.MyClass" et "foo.bar.yourclass.YourClass".

Les imports de jokers (`from <module> import *`) doivent être évités, car ils ne permettent pas de savoir clairement quels noms sont présents dans l'espace de noms, ce qui crée une confusion pour les lecteurs et de nombreux outils automatisés. Il existe un cas d'utilisation défendable pour un import avec joker, qui consiste à republier une interface interne dans le cadre d'une API publique (par exemple, l'écrasement d'une implémentation Python pure d'une interface avec les définitions d'un module accélérateur optionnel et les définitions exactes qui seront écrasées ne sont pas connues à l'avance).

Lors de la republication de noms de cette manière, les directives ci-dessous concernant les interfaces publiques et internes restent d'application.

### Module Level Dunder Names
Les "dunders" au niveau du module (c'est-à-dire les noms avec deux traits de soulignement avant et deux traits de soulignement arrière) tels que `__all__`, `__author__`, `__version__`, etc. doivent être placés après la docstring du module mais avant toute déclaration d'importation, sauf pour les importations `__future__`. Python impose que les imports futurs doivent apparaître dans le module avant tout autre code, à l'exception des docstrings :
```python
"""C'est un module d'exemple

Ce module fait des trucs.
"""

from __future__ import barry_as_FLUFL

__all__ = ['a', 'b', 'c']
__version__ = '0.1'
__author__ = 'Cardinal Biggles'

import os
import sys
```

### Délimiteurs de chaines
En python, les simple et doubles quotes sont identiques. Ce PEP ne fait aucune recommandations pour ça.
Prenez une règle et suivez la. Cependant quand une chaine contien une simple ou une double quote, utilisez l'autre pour eviter les anti-slash dans la chaine, ça aide la lisibilité.

pour les chaines a triples quote, utilisez le caractère `"` pour etre consistant avec la convention de chaine de documentation (docstring) du PEP 257.

### Espaces dans les expressions et les instructions
Evitez les espaces dans les  situations suivantes :
- Entre parenthèse, accolades ou crochets (1)
- Entre une virgule de fin et la parenthese subséquente (2)
- juste avant une virgule, point-virgule ou deux points (3)
- juste avant une parenthèse de fonction (4)
- pour aligner des opérateurs (5)
```python
# (1)

# Correcte:
spam(ham[1], {eggs: 2})
# Incorrecte:
spam( ham[ 1 ], { eggs: 2 } )

# (2)
# Correcte:
foo = (0,)
# Incorrecte:
bar = (0, )

# (3)
# Correcte:
if x == 4: print x, y; x, y = y, x
# Incorrecte:
if x == 4 : print x , y ; x , y = y , x

# (4)
# Correcte:
spam(1)
dct['key'] = lst[index]
# Incorrecte:
spam (1)
dct ['key'] = lst [index]

# (5)
# Correcte:
x = 1
y = 2
long_variable = 3
# Incorrecte:
x             = 1
y             = 2
long_variable = 3
```

Il est aussi recommandé de faire ce qui suit :

- Évitez de laisser des espaces n'importe-où. Comme il est généralement invisible, il peut prêter à confusion : par exemple, une barre oblique inversée suivie d'un espace et d'un saut de ligne ne compte pas comme marqueur de continuation de ligne. Certains éditeurs ne le conservent pas et de nombreux projets (comme CPython lui-même) ont des crochets de pré-commission qui le rejettent.
- Toujours précéder et faire suivre les opérateurs binaires d'un espace (=, +=, -= (et autres de ce type), ==, <, >, !=, <>, <=, >=, in, not in, is, is not, and, or, not)
- Si des opérateurs ayant des priorités différentes sont utilisés, pensez à ajouter des espaces autour des opérateurs ayant la ou les priorités les plus faibles. Utilisez votre propre jugement ; cependant, n'utilisez jamais plus d'un espace et ayez toujours la même quantité d'espace de part et d'autre d'un opérateur binaire :
```python
 Correcte:
i = i + 1
submitted += 1
x = x*2 - 1
hypot2 = x*x + y*y
c = (a+b) * (a-b)
# Incorrecte:
i=i+1
submitted +=1
x = x * 2 - 1
hypot2 = x * x + y * y
c = (a + b) * (a - b)
```
- Les anotations de fonctions suivent les règles normales des deux points et ont toujours des espaces autour de la flèche ( -> )
```python
# Correcte:
def munge(input: AnyStr): ...
def munge() -> PosInt: ...
# Incorrecte:
def munge(input:AnyStr): ...
def munge()->PosInt: ...
```
- N'utilisez pas d'espaces autour du signe = lorsqu'il est utilisé pour indiquer un argument de mot-clé, ou lorsqu'il est utilisé pour indiquer une valeur par défaut pour un paramètre de fonction non annoté 
```python
# Correcte:
def complex(real, imag=0.0):
    return magic(r=real, i=imag)
# Incorrecte:
def complex(real, imag = 0.0):
    return magic(r = real, i = imag)
```
Cependant lorsque sont liés anotation et valeur par défaut utiliser des espaces
```python
# Correcte:
def munge(sep: AnyStr = None): ...
def munge(input: AnyStr, sep: AnyStr = None, limit=1000): ...
# Incorrecte:
def munge(input: AnyStr=None): ...
def munge(input: AnyStr, limit = 1000): ...
```
... (pour plus d'ifo aller voir le PEP lié en haut, je m'arete la pour ça)
Parties skipées : fin d'espaces, virgules de fin

### Commentaires
Les commentaires qui contredisent le code sont pires que l'absence de commentaires. Gardez toujours en priorité les commentaires à jour lorsque le code change !

Les commentaires doivent être des phrases complètes. Le premier mot doit être en majuscule, sauf s'il s'agit d'un identifiant qui commence par une lettre minuscule (ne modifiez jamais la casse des identifiants !).

Les commentaires en bloc consistent généralement en un ou plusieurs paragraphes construits à partir de phrases complètes, chaque phrase se terminant par un point.

Vous devez utiliser deux espaces après un point de fin de phrase dans les commentaires composés de plusieurs phrases, sauf après la dernière phrase.

Assurez-vous que vos commentaires sont clairs et facilement compréhensibles pour les autres locuteurs de la langue dans laquelle vous écrivez.

Codeurs Python de pays non anglophones : veuillez écrire vos commentaires en anglais, sauf si vous êtes sûr à 120 % que le code ne sera jamais lu par des personnes qui ne parlent pas votre langue.

Les commentaires en bloc s'appliquent généralement à une partie (ou à la totalité) du code qui les suit, et sont en retrait au même niveau que ce code. Chaque ligne d'un bloc de commentaires commence par un # et un espace (sauf s'il s'agit d'un texte indenté à l'intérieur du commentaire).

Les paragraphes à l'intérieur d'un bloc de commentaires sont séparés par une ligne contenant un seul #.

tilisez les commentaires en ligne avec modération.

Un commentaire en ligne est un commentaire sur la même ligne qu'une déclaration. Les commentaires en ligne doivent être séparés de l'énoncé par au moins deux espaces. Ils doivent commencer par un # et un seul espace.

Les commentaires en ligne sont inutiles et en fait distrayants s'ils énoncent une évidence. Ne faites pas cela :
```python
x = x + 1                # Incremente x
```
Mais parfois ça peut-être utile :
```python
x = x + 1                 # Compener la frontière
```

Les conventions pour la rédaction de bonnes chaînes de documentation (alias "docstrings") sont immortalisées dans la PEP 257.

Écrivez des chaînes de documentation pour tous les modules, fonctions, classes et méthodes publics. Les docstrings ne sont pas nécessaires pour les méthodes non publiques, mais vous devez avoir un commentaire qui décrit ce que fait la méthode. Ce commentaire doit apparaître après la ligne def.

La norme PEP 257 décrit les bonnes conventions en matière de docstring. Notez que le plus important est que le """ qui termine une docstring multiligne doit être sur une ligne à lui seul :
```python
"""Return a foobang

Optional plotz says to frobnicate the bizbaz first.
"""
```
### Conventions de nommages
Comme le dit le PEP,les conventions de nommages en python sont assez peu précis, pour simplifier ici
Classe = CamelCase
variable = snake_case
CONSTANTE = SNAKE_CASE
fonction = snake_case

les noms de variables l, O et I sont à éviter.
pour plus de details, consuleter [la section du PEP0008](https://www.python.org/dev/peps/pep-0008/#naming-conventions)

#### Recommandations de code :
Le code ne doit pas etre écrit pour une implémentation particulière de python, par exemple éviter la concaténation en strin_a = string_a + string_b mais préférer la forme ''.join().

La comparaison a des Singleton tel que None doit être fait avec `is` ou `is not` mais pas avec des `==` ou `!=`

utiliser plutot l'opératour `is not` que `not...is`
```python
# Correcte:
if foo is not None:
# Incorrecte:
if not foo is None:
```

Lors de la mise en œuvre d'opérations de commande avec des comparaisons riches, il est préférable de mettre en œuvre les six opérations (`__eq__`, `__ne__`, `__lt__`, `__le__`, `__gt__`, `__ge__`) plutôt que de se fier à un autre code pour n'exercer qu'une comparaison particulière.

Pour minimiser l'effort nécessaire, le décorateur `functools.total_ordering()` fournit un outil permettant de générer les méthodes de comparaison manquantes.

PEP 207 indique que les règles de réflexivité sont assumées par Python. Ainsi, l'interpréteur peut échanger `y > x` avec `x < y`, `y >= x` avec `x <= y`, et peut échanger les arguments de `x == y` et `x != y`. Les opérations `sort()` et `min()` sont garanties d'utiliser l'opérateur `<` et la fonction `max()` utilise l'opérateur `>`. Toutefois, il est préférable de mettre en œuvre les six opérations afin d'éviter toute confusion dans d'autres contextes.

préferer la forme :
```python
def f(x): return 2*x
```
à
```python
f = lambda x: 2*x
```

Dériver les exceptions de `Exception` plutôt que de `BaseException`. L'héritage direct de `BaseException` est réservé aux exceptions dont la capture est presque toujours la mauvaise chose à faire.

Concevez des hiérarchies d'exceptions basées sur les distinctions dont le code de capture des exceptions est susceptible d'avoir besoin, plutôt que sur les endroits où les exceptions sont soulevées. Répondre à la question "Qu'est-ce qui a mal tourné ?" par la programmation, plutôt que de se contenter de dire "Un problème s'est produit" (voir PEP 3151 pour un exemple de cette leçon pour la hiérarchie d'exceptions intégrée)

Les conventions d'appellation des classes s'appliquent ici, bien que vous deviez ajouter le suffixe "Error" à vos classes d'exception si l'exception est une erreur. Les exceptions sans erreur qui sont utilisées pour le contrôle de flux non local ou d'autres formes de signalisation n'ont pas besoin de suffixe spécial.

Utilisez le chaînage des exceptions de manière appropriée. En Python 3, `raise X from Y` doit être utilisé pour indiquer un remplacement explicite sans perdre le traceback original.

Lorsque vous remplacez délibérément une exception interne (en utilisant `raise X` en Python 2 ou `raise X from None` en Python 3.3+), assurez-vous que les détails pertinents sont transférés à la nouvelle exception (comme la préservation du nom de l'attribut lors de la conversion de KeyError en AttributeError, ou l'incorporation du texte de l'exception originale dans le nouveau message d'exception).

Lorsque vous créez une exception en Python 2, utilisez `raise ValueError('message')` au lieu de l'ancienne forme `raise ValueError, 'message'`.

Cette dernière forme n'est pas une syntaxe Python 3 légale.

La forme paren-using signifie également que lorsque les arguments d'exception sont longs ou incluent un formatage de chaîne de caractères, vous n'avez pas besoin d'utiliser des caractères de suite de ligne grâce aux parenthèses qui les contiennent.

Lors de la saisie des exceptions, mentionnez des exceptions spécifiques dans la mesure du possible au lieu d'utiliser une simple clause `except` :
```python
try:
    import platform_specific_module
except ImportError:
    platform_specific_module = None
```

Une simple clause `except : ` permet de prendre en compte les exceptions SystemExit et KeyboardInterrupt, ce qui rend plus difficile l'interruption d'un programme avec Control-C, et peut masquer d'autres problèmes. Si vous voulez attraper toutes les exceptions qui signalent des erreurs de programme, utilisez `except Exception :`. (le simple `except :` est équivalent à `except BaseException :`).

Une bonne règle de base consiste à limiter l'utilisation de la simple clauses `except :` à deux cas :

1. Si le gestionnaire d'exceptions imprime ou enregistre le traceback, l'utilisateur sera au moins conscient qu'une erreur s'est produite.

2. Si le code doit faire un travail de nettoyage, mais laisse ensuite l'exception se propager vers le haut avec `raise`. `try...finally` peut être un meilleur moyen de gérer ce cas.

pour plus de recommandations,et la fin, je vous invite à lire [cette section du PEP](https://www.python.org/dev/peps/pep-0008/#programming-recommendations)
