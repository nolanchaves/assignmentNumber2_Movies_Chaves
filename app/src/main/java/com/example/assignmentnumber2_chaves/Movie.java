package com.example.assignmentnumber2_chaves;

public class Movie {

    private String title;
    private String rating;
    private String year;
    private String posterUrl;
    private String description;
    private String director;

    public Movie(String title, String rating, String year, String posterUrl, String description, String director) {
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.posterUrl = posterUrl;
        this.description = description;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
}
