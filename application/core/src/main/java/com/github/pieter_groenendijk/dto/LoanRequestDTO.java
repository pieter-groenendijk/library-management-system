package com.github.pieter_groenendijk.dto;

import java.time.LocalDate;

public class LoanRequestDTO {
    private LocalDate startDate = LocalDate.now();
    private long productCopyId;
    private long membershipId;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public long getProductCopyId() {
        return productCopyId;
    }

    public void setProductCopyId(long productCopyId) {
        this.productCopyId = productCopyId;
    }


    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }
}
