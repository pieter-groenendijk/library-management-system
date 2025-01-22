package com.github.pieter_groenendijk.model;

import java.util.Date;

public class Account {


    private Long accountId;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private char gender;
    private int uncollectedReservations;
    private boolean isBlocked;
    private boolean isDeleted;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) { isBlocked = blocked;}

    public int getUncollectedReservations() {
        return uncollectedReservations;
    }

    public void setUncollectedReservations(int uncollectedReservations) {this.uncollectedReservations = uncollectedReservations;}

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}