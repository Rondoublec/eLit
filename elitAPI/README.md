# eLitAPI : API de la bibliothéque municipale de Nîmes
API de gestion de bibliothèque.

### Contexte  
Cette API compose la V1 du système d'information des bibliothèques municipales d'une ville.  
Développé par Rémy Bourdoncle, il s'agit du 7eme projet du cursus Développeur d'application Java proposé par OpenClassrooms.  

### Contenu
Cette API gère les comptes utilisateurs, les ouvrages et les emprunts

### Pré-requis technique  
Version de Java : 1.8  
JDK : jdk1.8.0_191  
Maven 3.6  
### Base de données  
PostgresSQL

### Documentation
http://localhost:8088/swagger-ui.html  
la javadoc peut être consultée en lançant le fichier **docs\index.html**  

##Installation et déploiement
Packaging : **mvn clean package**

Aller dans target et lancer le war avec la commande
**java -jar elitapi-0.0.1-SNAPSHOT.war**

Le port de l'Application est paramétré dans application.propertie  : `http://localhost:8088/`  
Par défaut mode **dev** (base mémoire H2), la base est peuplée avec un jeu de données de tests.  
  
L'application est livrée avec 2 configurations   
- **dev** avec une base de données en mémoire (H2) créée à chaque lancement et peuplée avec le contenu du script src\resources\data.sql.  
 Les mots de passes sont dans le data.sql  
- **prod** avec une base de données PostgreSQL à peupler. Lors du 1er lancement pour créer le modèle il faut mettre à **creat-drop** le paramètre spring.jpa.hibernate.ddl-auto dans le fichier application.properties correspondant.
Pour conserver le contenu aux lancements suivants positionnez à **update** la valeur de ce paramètre.
*Vous devez tout de même au préalable avoir créé la base de données avec son compte de propriétaire -> ces informations seront à mettre à jour dans le fichier application.properties correspondant à la configuration de l'application.*

**Logs :** Par défaut le niveau de log est positionné à "INFO", les logs sont quotidiens (hordatés) et se trouvent dans le répèrtoire **logs**, tous ces paramétrages sont dans le fichier **src\main\resources\logback.xml**


Sources disponibles sur : https://github.com/Rondoublec/eLit-V2  
La partie API est sous :  https://github.com/Rondoublec/eLit-V2/tree/master/elitAPI
