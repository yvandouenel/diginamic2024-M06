# Explications sur la construction de l'application
## Container app
 Vous voyez que leDockerfile utilise ce qu'on appelle le "multi-stage build" (construction en plusieurs étapes), une pratique très courante pour optimiser la taille finale de l'image Docker.
### Première étape (BUILD)

```dockerfile
FROM maven:3.8.4-openjdk-11-slim AS build
```
Cette étape utilise une image Maven qui inclut le JDK 11 (nécessaire pour compiler)
Elle est nommée "build" grâce au AS build
Cette image est plus lourde car elle contient Maven et tout l'environnement de compilation

```dockerfile
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
```
On copie les sources et on compile le projet (via la commande package) Java avec Maven


### Deuxième étape (RUNTIME)

```dockerfile
FROM openjdk:11-jre-slim
```
On repart d'une nouvelle image, plus légère, qui contient uniquement le JRE (Java Runtime Environment)
Le JRE est suffisant pour exécuter l'application (pas besoin du JDK complet)
```dockerfile
dockerfileCopyCOPY --from=build /app/target/movie-db-1.0-SNAPSHOT-jar-with-dependencies.jar ./app.jar
```
On copie UNIQUEMENT le fichier JAR compilé depuis l'étape précédente

L'avantage de cette approche :

L'image finale ne contient que le strict nécessaire (JRE + votre JAR)
On ne garde pas Maven, ni les sources, ni les dépendances de build
L'image finale est beaucoup plus légère (potentiellement 100-200MB au lieu de 500-700MB)
C'est plus sécurisé car il y a moins de composants dans l'image finale
## Container db
Ce container est nettement plus classique, il est basé sur une image mysql et permettra donc de stocker les informations

# But de l'application
## Phase 1
Créer un container MySQL prêt pour des connexions
Créer un container d'application Java qui se connecte à la base de données
L'application :
 - insère le film "Inception" dans la base
 - récupère et affiche la liste des films
 - se termine normalement (code 0)

 ## Phase 2
 Possibilité de :
 - voir la liste des films
 - ajouter un film
 - quitter l'application


Pour rendre l'application plus interactive, on pourrait :

Ajouter une boucle dans le Main pour garder l'application active
Mettre en place une API REST avec Spring Boot
Créer une interface utilisateur web
# Lancer l'application la première fois
```bash
docker-compose up
```

# Relancer l'application
```bash
docker-compose down -v
docker-compose build --no-cache
docker-compose up
```
où 
 - down -v permet de supprimer les volumes
 - build --no-cache : Construit les images Docker et ignore le cache du dockerfile


