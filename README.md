# OcrP12
    P12 - OCR DA JavaEE
    JDK : 13
    BDD : postgresql
### Connexion site
    User : h11111
    mdp : a
### Connexion Base de données :
    
    User : ocrp12
    Pwd  : root
    Structure : 
    une base de données (ocrp12_dev,ocrp12_prod ou ocrp12_test selon le profil) avec un shéma pour chaque microservice

### Etapes de déploiement

    Attention bien suivre l'ordre de déploiement
    3 profils : dev, prod et test

#### Etape 1 - Les edges microservices
    1 - déployer config-server
    2 - déployer eureka-server  
---
#### Etape 2 - API Gateway
    1 - déployer zuul-server
---
#### Etape 3 - Les microservices
    1 - déployer agence
    2 - déployer files
    3 - déployer marche 
    4 - déployer prestataire
    5 - déployer qualite
    6 - déployer users
    7 - déployer web

### Etape finale
    Lancer zipkin-server
    url: http://localhost:9411
    
#### Les tests
    Pour effectuer les différents tests il est nécessaire de faire le déploiement avec le profile test
    => -Dspring.profiles.active=test
    - les microservices marche,prestataire et users disposent des tests controller, repository et service. Exécuter la commande mvn verify sur le microservice souhaité pour lancer les différents tests
    
### API Insee
    Site de l'API : https://api.insee.fr/
    
    Commande curl pour générer a la consumer-key (penser à remplacer consumer-secret par votre propre key) :
    
    curl -k -d "grant_type=client_credentials" \
	    -H "Authorization: Basic Base64(consumer-key:consumer-secret)" \
	     https://api.insee.fr/token
	 La key générée est à mettre à jour dans web.properties ( config /dev /prod /test du repository git) => api.insee.key
	     
### Jenkins
    La configuration des builds sont disponibles dans les livrables ( répertoire Jenkins) avec les scripts batch windows.
    Etape 1 : Contruiser les builds selon les configurations disponibles dans P12 - Configuration Jenkins.docx
    Etape 2 : depuis lancer uniquement le build P12_ConfigServer. Durée total environ 10 à 15mn
    Les différents services sont générés dans le répertoire C:\Users\Public\deploiement
    Etape Start : les commandes de démarrage des services pour chaque profil sont disponibles dans le Répertoire Start Microservices 
### Livrables
    Document exemple : Différents documents qui vont servir pour la présentation
    Jenkins : La configuration de Jenkins
    OcrP12 : code source du projet
    SQL : Les requêtes Sql permettant de générer les bases de données ainsi que des jeux de données
    Start Microservices : Les différents Scripts Bash permettant le démarrage des différents services (bien respecter l'order de lancement)
    Fichier GitHub : url git du projet OcrP12
    Présentation du projet : Soutenance - DAJava EE - Projet 12 - Romain-David Sergeant v1.pptx
 
    
