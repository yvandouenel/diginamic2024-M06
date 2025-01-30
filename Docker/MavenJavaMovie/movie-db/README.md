# But de l'application

Créer un container MySQL prêt pour des connexions
Créer un container d'application Java qui se connecte à la base de données
L'application :
 - insère le film "Inception" dans la base
 - récupère et affiche la liste des films
 - se termine normalement (code 0)


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
