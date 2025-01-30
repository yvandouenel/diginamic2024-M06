package com.example;

import com.example.model.Movie;
import com.example.repository.MovieRepository;

public class Main {
    public static void main(String[] args) {
        MovieRepository repository = new MovieRepository();

        // Ajout d'un film
        Movie inception = new Movie(null, "Inception", "Christopher Nolan", "Leonardo DiCaprio, Ellen Page");
        repository.createMovie(inception);

        // Affichage de tous les films
        System.out.println("Liste des films :");
        repository.getAllMovies().forEach(movie -> 
            System.out.println(movie.getTitle() + " - " + movie.getScreenwriter() + " - " + movie.getMainActors())
        );
    }
}