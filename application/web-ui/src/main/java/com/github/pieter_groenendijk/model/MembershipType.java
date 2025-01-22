package com.github.pieter_groenendijk.model;

public class MembershipType {

    private Long membershipTypeId;
    private String description;
    private boolean digitalProducts;
    private boolean physicalProducts;
    private int maxLendings;
    private boolean isDeleted;

    // Getters and Setters
    public Long getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(Long membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDigitalProducts() {
        return digitalProducts;
    }

    public void setDigitalProducts(boolean digitalProducts) {
        this.digitalProducts = digitalProducts;
    }

    public boolean isPhysicalProducts() {
        return physicalProducts;
    }

    public void setPhysicalProducts(boolean physicalProducts) {
        this.physicalProducts = physicalProducts;
    }

    public int getMaxLendings() {
        return maxLendings;
    }

    public void setMaxLendings(int maxLendings) {
        this.maxLendings = maxLendings;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
