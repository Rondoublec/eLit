# eLitClient : site web de la bibliothéque municipale de Nîmes pour les abonnées
Site web de la bibliothèque

### Contexte  
Ce site est à destination des adhérents, il compose la V1 du système d'information des bibliothèques municipales d'une ville.  
Développé par Rémy Bourdoncle, il s'agit du 7eme projet du cursus Développeur d'application Java proposé par OpenClassrooms.  

### Contenu
Ce site permet aux adhérents de rechercher des ouvrages, de consulter l'état de leurs emprunts et de prolonger leurs emprunts en cours

### Pré-requis technique  
Version de Java : 1.8  
JDK : jdk1.8.0_191  
Maven 3.6  

### Documentation
la javadoc peut être consultée en lançant le fichier **docs\index.html**  

##Installation et déploiement
Packaging : **mvn clean package**

Aller dans target et lancer le war avec la commande
**java -jar eliclient-0.0.1-SNAPSHOT.war**
Le port de l'Application est paramétré dans application.propertie  : `http://localhost:8089/`  

**Logs :** Par défaut le niveau de log est positionné à "INFO", les logs sont quotidiens (hordatés) et se trouvent dans le répèrtoire **logs**, tous ces paramétrages sont dans le fichier **src\main\resources\logback.xml**


Sources disponibles sur : https://github.com/Rondoublec/eLit-V2  
La partie front est sous :  https://github.com/Rondoublec/eLit-V2/tree/master/eLitClient
