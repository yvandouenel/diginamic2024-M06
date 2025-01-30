package com.example.repository;

import com.example.model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private final String url;
    private final String user;
    private final String password;

    public MovieRepository() {
        this.url = "jdbc:mysql://db:3306/moviedb";
        this.user = "user";
        this.password = "password";
    }

    public void createMovie(Movie movie) {
        String sql = "INSERT INTO movies (title, screenwriter, main_actors) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getScreenwriter());
            pstmt.setString(3, movie.getMainActors());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                movies.add(new Movie(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("screenwriter"),
                    rs.getString("main_actors")
                ));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return movies;
    }
}