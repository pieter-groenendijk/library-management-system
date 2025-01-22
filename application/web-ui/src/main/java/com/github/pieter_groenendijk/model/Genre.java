package com.github.pieter_groenendijk.model;


public class Genre {

    private long genreId;


    private String description;

    public long getGenreId() { return genreId; }
    public void setGenreId(long genreId) {this.genreId = genreId; }
    public String getDescription() {return description; }
    public void setDescription(String description) {this.description = description; }
}