Ekino @DevoxxFR
========

Pour participer au jeu concours d'Ekino à DevoxxFR 2013 à partir du template EKino, voici la listes des étapes à suivre :


##Prérequis :
* une JDK 7 (sun, openjdk ...)
* un client git
* un éditeur de text ou un IDE, tel eclipse ou Intellij IDEA
* posséder un compte github pour la soumission


## Facultatif (mais fortement recommandé)
* [installer Play Framework 2.1](http://downloads.typesafe.com/play/2.1.0/play-2.1.0.zip)
* ajouter dans votre PATH, le répertoire bin de votre installation Play

```bash
$ export PATH=$PATH:/CHEMIN_INSTALL_PLAY/play`
```

* tester votre commande :

```bash
$ play help
```

## Préambule
* Forker ce repo : [Joueur Ekino concours](https://github.com/ekino/starfight)

* Cloner votre fork:
```bash
git clone git@github.com:<mygithublogin>/starfight
```

* Pour vérifier son bon fonctionnement si vous avez installé play : 

```bash
$ play test
```

* Le projet comporte déjà les configurations nécessaires pour Eclipse et Idea, mais vous pouvez utilisez un éditeur classique.
	* Sur Eclipse, il faut utiliser l'outil d'import de projet.
	* Avec Idea, il faut seulement ouvrir le projet. Dans le cas, ou vous rencontrez des problèmes avec le classpath, vous pouvez essayer de regénérer le .idea
		
```bash
$ play idea
```

* Renseigner votre clé secrête qui sera partagée avec le serveur de jeu et que vous devriez fournir sur le formulaire de soumission
	* ouvrer conf/application.conf et changer la valeur de la clé (YOURKEY) 'application.secret'


## Développement
Tout se fait dans la classe `app/core/PlayerAlgorithmDefault.java`.   
Vous avez accès au source de la classe cliente dans le répertoire lib


### pubic String ping()
Il faut en premier compléter votre pseudo de la méthode ping. Ce pseudo devra par ailleurs être fourni sur le formulaire de soumission.
Elle est utilisée par le serveur de jeu pour vérifier votre que votre serveur est UP.


### ActionList turn(World world)
Cette méthode est appelée à chaque tour de jeu.  
En entrée les informations concernant votre armée - ou du moins les vaisseaux qui répondent encore à l’appel - et les résultats des scanners des vaisseaux.   
En sortie vous devez fournir une liste d’action à faire effectuer à vos vaisseaux.  
Deux types d’actions sont réalisables :   
- Attaque : chaque vaisseau peut tirer une fois par tour   
- Déplacement : chaque vaisseau a droit à 5 cases de déplacement fractionnables dans les 4 directions Nord, Sud, Est, Ouest.    
Conseil : le numéro du tour est envoyé à chaque tour dans World. Si vous n’êtes pas sans état au niveau de votre serveur, pensez à réinitialiser vos données si vous recevez 0.   


### Aide Git
* Pour ajouter vos fichiers au prochain commit (si nouveaux fichiers surtout)

```bash
$ git add .
```

* Pour commiter directement :

```bash
$ git commit -am 'VOTRE COMMENTAIRE'
```

* Pour envoyer sur votre depot GitHub

```bash
$ git push VOTRE_REMOTE master
```


## Outils de tests
* Pour s'assurer que son application fonctionne toujours, dans le cas où vous avez installé Play : 

```bash
$ play test
```

* Vous pouvez lancer en local, votre projet pour le tester :

```bash
$ play run
```

* Des commandes dans le répertoire bin, ping.sh et turn.sh sont disponibles pour que vous puissiez valider votre application lorsqu'elle est déployée, ex :  

```bash
$ ./bin/ping.sh http://localhost:9000 YOUR_KEY
$ ./bin/turn.sh http://localhost:9000 YOUR_KEY
```


## Déploiement sur Heroku
* Ne pas oublier d'avoir commiter vos modifications avant de déployer
* [créer un compte sur Heroku](https://api.heroku.com/signup/devcenter)
* installer le [toolbel Heroku](https://toolbelt.heroku.com/), ou avec cette commande pour linux :

```bash
$ wget -qO- https://toolbelt.heroku.com/install-ubuntu.sh | sh
```

* Ensuite, il faut s'authentifier en tapant : 

```bash
$ heroku login
```

Vous pouvez utilisez une de vos clés SSH, soit laisser Heroku en générer une pour vous.
* A la racine de votre projet, taper : 

```bash
$ heroku create
```

* Pour déployer l'application sur Heroku : 

```bash
$ git push heroku master
```

* Vous pouvez valider votre déploiement en utilsant les outils de test ./bin/ping.sh et ./bin/turn.sh vu ci-dessus   

Référence documentation officielle : 
* [Getting started](https://devcenter.heroku.com/articles/quickstart#step-1-sign-up)
* [déployer une application Play](https://devcenter.heroku.com/articles/play)


## Déploiement sur Cloudbees
* Ne pas oublier d'avoir commiter vos modifications avant de déployer
* [créer](https://grandcentral.cloudbees.com/login) ou posséder un compte cloudbees
* installer le bees SDK :

```bash
$ curl -L cloudbees-downloads.s3.amazonaws.com/sdk/cloudbees-sdk-1.3.1-bin.zip > bees_sdk.zip
$ unzip bees_sdk.zip
$ rm bees_sdk.zip
$ cd cloudbees-sdk-1.3.1
```

* ajouter dans votre PATH, bees SDK

```bash
$ export BEES_HOME=~/cloudbees-sdk-1.3.1
$ export PATH=$PATH:$BEES_HOME
```

* Pour déployer sur cloudbees à la racine du projet starflight

```bash
$ play dist
$ bees app:deploy -a YOUR_APP -t play2 -Rjava_version=1.7 dist/starflight-1.0-SNAPSHOT.zip
```

* Vous pouvez valider votre déploiement en utilsant les outils de test ./bin/ping.sh et ./bin/turn.sh vu ci-dessus


## Soumission
* Allez sur http://devoxx.ekino.com/account/subscribe
Remplissez tous les champs,
	* Pseudo : le pseudo que vous avez mis dans la méthode ping() , en respectant la casse
	* apiKey : la clé secrète que vous avez mise dans conf/application.conf, en respectant la casse.
	* email : un email valide, un mail vous sera envoyé pour validation suite à la soumission
        * nom et prénom: pour affichage!
	* github : l’url de votre fork github sur lequel nous pouvons consulter votre algorithme
	* url de votre instance : l’url heroku (ou autre) où vous avez déployé votre serveur

Si votre serveur répond au ping, vous serez alors considéré comme validé.
Allez sur le stand Ekino pour faire concourir votre algorithme.

