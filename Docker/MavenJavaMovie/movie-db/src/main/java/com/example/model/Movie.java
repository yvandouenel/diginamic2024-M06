package com.example.model;

public class Movie {
    private Long id;
    private String title;
    private String screenwriter;
    private String mainActors;

    // Constructeurs, getters et setters
    public Movie(Long id, String title, String screenwriter, String mainActors) {
        this.id = id;
        this.title = title;
        this.screenwriter = screenwriter;
        this.mainActors = mainActors;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getScreenwriter() { return screenwriter; }
    public void setScreenwriter(String screenwriter) { this.screenwriter = screenwriter; }
    public String getMainActors() { return mainActors; }
    public void setMainActors(String mainActors) { this.mainActors = mainActors; }
}