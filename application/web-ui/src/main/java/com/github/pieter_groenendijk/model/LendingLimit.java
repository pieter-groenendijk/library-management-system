package com.github.pieter_groenendijk.model;

import com.github.pieter_groenendijk.model.Genre;

public class LendingLimit {

    private long lendingLimitId;
    private MembershipType membershipType;
    private Genre genre;
    private int maxLendings;

    public long getLendingLimitId() {return lendingLimitId; }
    public void setLendingLimitId(long lendingLimitId) {this.lendingLimitId = lendingLimitId;}
    public MembershipType getMembershipType() {return membershipType;}
    public void setMembershipType(MembershipType membershipType) {this.membershipType = membershipType;}
    public Genre getGenre() {return genre;}
    public void setGenre(Genre genre) {this.genre = genre;}
    public int getMaxLendings() {return this.maxLendings;}
    public void setMaxLendings(int maxLendings) {this.maxLendings = maxLendings;}
}