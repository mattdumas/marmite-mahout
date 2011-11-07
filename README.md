Marmite - Mahout
===============

Application de base pour la hands sur Apache Mahout (Algorithme de recommandations).

La but du HandsOn est d'utiliser Apache mahout avec 2 de ces algorithmes les plus intéressants permettant de faire
de la recommandation.


Installation
----------------

* Installez git (`sudo apt-get install git-core`)
* Installez Java 1.6.x (java 1.7.x ne fonctionne pas)
* Installez Play! Framework 1.2.x http://www.playframework.com
* Tapez `git clone git://github.com/LateralThoughts/marmite-mahout.git` pour cloner ce repository
* Tapez `play dependencies` pour mettre à jour les dépendances
* Tapez `play run` à la racine du projet pour lancer le serveur d'application
* Allez sur  http://localhost:9000


Préambule
----------------
Nous voici sur le site de recommandation de la Marmite, ce site permet de se connecter, d'ajouter des recettes et de
rechercher les recettes saisies par l'utilisateur connecté ou les autres utilisateurs.

* Connectez vous (vous devez avoir un compte google)
* Ajoutez une ou plusieurs recettes
* Recherchez un recette.

Nous allons améliorer le site pour découvrir de nouvelles recettes en fonction de nos goûts.

* Tapez `play eclipsify` ou `play idealize` à la racine du projet pour créer le projet pour votre IDE de prédilection.
* Ouvrez votre IDE
* Ouvrez le projet.


Etape 1
----------------
Comme tout programmeur qui se respecte, nous allons commencer par écrire un test unitaire.

* Ouvrez la classe BasicTest
* Analyser le test : testRecommendation() et compléter les 4 lignes commentées avec le résultat que vous espérez.
(Les autres tests sont là pour vous aider et ne nécessite pas de modification.)


Etape X
----------------
Bravo ! Maintenant passons au niveau supérieur et implementons une système de recommandation d'utilisateur. Le but et de
proposer des utilisateurs qui ont les mêmes goût que l'utilisateur connecté.

Premièrement, écrivez un test unitaire comme nous l'avons fait pour l'étape 1.


Tips
----------------
* Pour rentre vos saisies de recettes resistante au redemmarrage du serveur : changer db=mem to db=fs dans le fichier application.conf
* Vous pouvez vous inspirer des fichiers .bashrc et .gitconfig ici : https://github.com/jblemee/config
pour avoir un meilleur environnement de dévellopement sous git
* pour lancer les tests play! `play autotest`



