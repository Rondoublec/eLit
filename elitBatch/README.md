# eLitBatch : batch de traitement des relances pour les retards de restitution des ouvrages empruntés

### Contexte  
Cette application est à destination des adhérents, il compose la V1 du système d'information des bibliothèques municipales d'une ville.  
Il s'agit du 7eme projet du cursus Développeur d'application Java proposé par OpenClassrooms.
Développé par Rémy Bourdoncle. 

### Contenu
A une fréquence quotidienne (paramétrable).  
Ce traitement recherche les ouvrages non restitués par adhérents, et dont la date de restitution est dépasée.  
Et envoi à chaque adhérent concerné un eMail lui rapelant la liste des ouvrage pour lesquels il est en retard de restitution.

### Pré-requis technique  
Version de Java : 1.8  
JDK : jdk1.8.0_191  
Maven 3.6  

### Documentation
la javadoc peut être consultée en lançant le fichier **docs\index.html**  

##Installation et déploiement
Packaging : **mvn clean package**  
Lors de la build des tests automatisés sont lancés, ils nécessittent pour fonctionner que l'API soit en fonctionnement et que le serveur SMTP opérationel (pour vos tests vous pouvez utiliser http://nilhcem.com/FakeSMTP/).  
Si vous ne voulez pas lancer les tests vous pouver packager avec le paramètre  
**mvn clean package -DskipTests**  


Aller dans target et lancer le jar avec la commande
**java -jar eLitBatch-0.0.1-SNAPSHOT.jar**

**Logs :** Par défaut le niveau de log est positionné à "INFO", les logs sont quotidiens (hordatés) et se trouvent dans le répèrtoire **logs**, tous ces paramétrages sont dans le fichier **src\main\resources\logback.xml**

Sources disponibles sur : https://github.com/Rondoublec/eLit-V2  
La partie front est sous : https://github.com/Rondoublec/eLit-V2/tree/master/elitBatch
