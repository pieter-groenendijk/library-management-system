package com.github.pieter_groenendijk.model;


public abstract class PhysicalProductTemplate extends ProductTemplate {

    private String location;

    private String author;


    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
