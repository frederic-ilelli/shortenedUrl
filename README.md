# Shortened Url

Le service URL Shortener est une application web simple mais puissante qui permet aux utilisateurs de raccourcir des URL longues en des liens courts et faciles à partager. Que vous souhaitiez partager des liens sur les réseaux sociaux, dans des e-mails ou dans des messages, notre service vous permet de réduire la longueur de vos URL pour une meilleure accessibilité et une meilleure gestion.

### Pré-requis

- **Java 21** : Assurez-vous d'avoir Java 21 installé sur votre système. Vous pouvez vérifier votre version de Java en exécutant la commande `java -version` dans votre terminal.
- **Gestionnaire de Dépendances** : Vous aurez besoin du gestionnaire de dépendances Maven pour gérer les dépendances du projet. Assurez-vous d'avoir Maven installé sur votre système (`mvn -version`).
- **Base de données** : Le projet utilise PostgreSql comme base de données. Assurez-vous d'avoir PostgreSql installé sur votre système ou d'accéder à une base de données MySQL disponible pour stocker les liens raccourcis.

### Installation

#### 1. Java 21

Assurez-vous d'avoir Java 21 installé sur votre système. Vous pouvez télécharger et installer Java 21 à partir du site officiel d'Oracle ou utiliser un gestionnaire de versions comme SDKMAN! pour installer et gérer différentes versions de Java sur votre machine.

- Télécharger Java 21 à partir du site officiel d'Oracle : [Java Downloads](https://www.oracle.com/fr/java/technologies/downloads/)

#### 2. Gestionnaire de Dépendances

Le projet utilise Maven comme gestionnaire de dépendances. Assurez-vous d'avoir Maven installé sur votre système. Vous pouvez télécharger Maven à partir du site officiel d'Apache Maven et suivre les instructions d'installation.

- Télécharger Maven à partir du site officiel d'Apache Maven : [Apache Maven Downloads](https://maven.apache.org/download.cgi)

#### 3. Base de Données MySQL

Le projet utilise PostgreSql comme base de données. Assurez-vous d'avoir PostgreSql installé sur votre système ou d'accéder à une base de données MySQL disponible pour stocker les liens raccourcis.

- Télécharger PostgreSql à partir du site officiel et suivre les instructions d'installation : [PostgreSql Downloads](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

## Démarrage

#### 1. Configuration de la Base de Données
Avant de démarrer le projet, assurez-vous d'avoir configuré les détails de votre base de données dans le fichier `application.properties`. Vous devrez spécifier le nom d'utilisateur, le mot de passe, l'URL de la base de données et d'autres propriétés pertinentes. Toutes ces propriétés devront être renseignées dans un fichier `env.properties` à la racine du projet avec trois variables.

- DB_DATABASE=[variable]
- DB_USER=[variable]
- DB_PASSWORD=[variable]

#### 2. Migration de la Base de Données avec Flyway

Le projet utilise Flyway comme gestionnaire de migration de base de données. Avant de lancer l'application, assurez-vous que toutes les migrations de base de données ont été exécutées avec succès. Vous pouvez exécuter les migrations en utilisant la commande Maven suivante :

`mvn flyway:migrate`

#### 3. Compilation et Exécution du Projet

Une fois que la base de données est configurée et que les migrations ont été appliquées avec succès, vous pouvez compiler et exécuter le projet. Utilisez les commandes Maven appropriées pour compiler et exécuter le projet, ou démarrez l'application à partir de votre IDE.

- Compilation du projet : `mvn clean install`
- Execution du projet : `mvn spring-boot:run`

#### 3. Accès à l'application
Une fois que l'application est lancée avec succès, vous pouvez accéder à l'interface utilisateur dans votre navigateur web à l'adresse suivante :
`http://localhost:8080`

## Fabriqué avec

* [Spring](https://spring.io/projects/spring-boot/) - Framework Java
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE