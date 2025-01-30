package com.example;

import com.example.model.Movie;
import com.example.repository.MovieRepository;
import java.util.Scanner;

public class Main {
    private static MovieRepository repository = new MovieRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    listMovies();
                    break;
                case "2":
                    addMovie();
                    break;
                case "q":
                case "Q":
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nGestion des films - Que voulez-vous faire ?");
        System.out.println("1. Voir la liste des films");
        System.out.println("2. Ajouter un film");
        System.out.println("Q. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void listMovies() {
        System.out.println("\nListe des films :");
        var movies = repository.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("Aucun film dans la base de données.");
            return;
        }
        movies.forEach(movie -> 
            System.out.println(movie.getTitle() + " - " + movie.getScreenwriter() + " - " + movie.getMainActors())
        );
    }

    private static void addMovie() {
        System.out.println("\nAjout d'un nouveau film");
        
        System.out.print("Titre : ");
        String title = scanner.nextLine();
        
        System.out.print("Scénariste : ");
        String screenwriter = scanner.nextLine();
        
        System.out.print("Acteurs principaux (séparés par des virgules) : ");
        String actors = scanner.nextLine();

        Movie movie = new Movie(null, title, screenwriter, actors);
        repository.createMovie(movie);
        System.out.println("Film ajouté avec succès !");
    }
}