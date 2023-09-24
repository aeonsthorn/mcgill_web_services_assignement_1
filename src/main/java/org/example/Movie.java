package org.example;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {

    private String ImdbId;
    private String Title;
    private String Description;
    private double Rating;

    private String genre;

    private int Year;
    private Date LastModified;

    public Movie(){}


    public String getImdbId(){
        return this.ImdbId;
    }
    public void setImdbId(String ImdbId){
        this.ImdbId = ImdbId;
    }

    public void setDescription(String description){
        this.Description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ImdbId='" + ImdbId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Rating=" + Rating +
                ", genre='" + genre + '\'' +
                ", Year=" + Year +
                ", LastModified=" + LastModified +
                '}';
    }
}
