package org.example;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movie implements Serializable {

    private String ImdbId;
    private String Title;
    private String Description;
    private BigDecimal Rating;
    private String Genre;

    private int Year;
    private LocalDateTime LastModified;

    public Movie() {
    }

    public String getImdbId() {
        return this.ImdbId;
    }

    public void setImdbId(String ImdbId) {
        this.ImdbId = ImdbId;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setRating(BigDecimal rating) {
        this.Rating = rating;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public void setYear(int year) {
        this.Year = year;
    }


    public void updateLastModified() {
        this.LastModified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ImdbId='" + ImdbId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Rating=" + Rating +
                ", Genre='" + Genre + '\'' +
                ", Year=" + Year +
                ", LastModified=" + LastModified +
                '}';
    }
}
