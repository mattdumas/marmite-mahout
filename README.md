Marmite - Mahout
===============

Application de base pour la hands sur Apache Mahout (Algorithme de recommandations).

La but du HandsOn est d'utiliser Apache mahout avec l'un de ces algorithmes les plus intéressants permettant de faire
de la recommandation.


Installation
----------------

* Installez git (`sudo apt-get install git-core`)
* Installez Java 1.6.x (java 1.7.x ne fonctionne pas avec ce projet)
* Installez Play! Framework 1.2.x http://www.playframework.com
* Tapez `git clone git://github.com/LateralThoughts/marmite-mahout.git` pour cloner ce repository
* Tapez `play dependencies` pour mettre à jour les dépendances
* Tapez `play run` à la racine du projet pour lancer le serveur d'application
* Allez sur  http://localhost:9090


Préambule
----------------
Nous voici sur le site de recommandation de la Marmite, ce site permet de se connecter, d'ajouter des recettes et de
rechercher les recettes saisies par l'utilisateur connecté ou les autres utilisateurs.

* Connectez vous (vous devez avoir un compte google)
* Ajoutez une ou plusieurs recettes
* Recherchez une recette.

Nous allons améliorer le site pour découvrir de nouvelles recettes en fonction de nos goûts.

* Tapez `play eclipsify` ou `play idealize` à la racine du projet pour créer le projet pour votre IDE de prédilection.
* Ouvrez votre IDE
* Ouvrez le projet.


Etape 1
----------------
Comme tout programmeur qui se respecte, nous allons commencer par écrire un test unitaire qui doit planter car l'implémentation
sera écrite à l'étape 2.

* Ouvrez la classe BasicTest
* Analyser le test : testRecommendation() :
* Etudiez le jeu de test et complétez avec le résultat que vous paraît le plus logique. A cette étape ce n'est pas
important si le test se révèle inexacte. L'objectif est de comprendre ce que l'on veut faire. N'hésitez pas à demander
de l'aide si vous avez des doutes !


Etape 2
----------------
Maintenant il s'agit de faire passer le test que nous venons d'écrire !
Pour cela, nous allons écrire le corps de la méthode Reco._internalRecommend

* Apache mahout propose l'interface : org.apache.mahout.cf.taste.recommender.Recommender
ainsi que de nombreuses implementation listées dans le package : org.apache.mahout.cf.taste.impl.recommender

* Choisissez celle qui vous semble la plus adaptées à votre test et tester !

* Vous aurez besoin de contruire un objet org.apache.mahout.cf.taste.model.DataModel à partir des données :
regardez les implémentations fournies dans le package : org.apache.mahout.cf.taste.impl.model

Pour choisir entre une recommendation basée sur les Users ou sur les Items, jetez un oeil ici : https://cwiki.apache.org/MAHOUT/recommender-documentation.html
et demander nous des explications.


Etape 3
----------------
* Lancer `play autotest` pour vérifier que tous les tests unitaire passent
* Allez sur le site, taper F5 pour rafraichir la page
* Saisissez plusieurs recettes avec plusieurs utilisateurs différents
* Recherchez des recettes existantes (taper "les" dans la recherche) indiquez si vous en aimez.
* Vous devriez obtenir des recommendations de recettes qui correspondent au gout de l'utilisateur connecté.


Etape 4
----------------
S'il vous reste du temps, plusieurs améliration sont possibles :
- Recommander des utilisateurs (Très facile)
- Noter les recettes et se servir de la note pour affiner le système (Facile)
- Utiliser ItemSimilarity pour recommander à partir de similarité entre recette (Difficile). Idée : Utiliser Lucene et l'auteur de la recette pour calculer les similarités

* Ecrivez au moins un test unitaire comme nous l'avons fait pour l'étape 1.
* implementez le controleur sur le même modèle que Reco._internalRecommend
* C'est fini ! Vous pouvez vous amuser à écrire la partie web cliente si il vous reste du temps et du courage !


Tips
----------------
* Pour lancer les tests play! `play autotest`
* En cas de blocage : Demandez nous ! ou tricher en allant sur github: regardez la liste des commits, certains sont libellés "Etapes X" et vous donnerons des pistes.
* Vous pouvez vous inspirer des fichiers .bashrc et .gitconfig ici : https://github.com/jblemee/config
pour avoir un meilleur environnement de dévelopement sous git.
* Ne perdez pas de temps avec Play! et Git : demandez nous les commandes et prévenez nous si vous avez un blocage.




