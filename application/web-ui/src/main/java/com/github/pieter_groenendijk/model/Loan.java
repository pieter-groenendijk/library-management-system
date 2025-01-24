package com.github.pieter_groenendijk.model;


import java.time.LocalDate;


public class Loan {

    private long loanId;

    private LocalDate startDate;

    private LocalDate returnBy;


    private LocalDate extendedReturnBy;

    private LocalDate returnedOn;


    private LoanStatus loanStatus;

    private Membership membership;


    private ProductCopy productCopy;


    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(long l) {
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Long getProductCopy() {
        return productCopy.getProductCopyId();
    }

    public void setProductCopy(ProductCopy productCopy) {
        this.productCopy = productCopy;
    }

    public LocalDate getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(LocalDate returnBy) {
        this.returnBy = returnBy;
    }

    public LocalDate getExtendedReturnBy() {
        return extendedReturnBy;
    }

    public void setExtendedReturnBy(LocalDate extendedReturnBy) {
        this.extendedReturnBy = extendedReturnBy;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }

    public void setProductCopyId(ProductCopy productCopy) {
        this.productCopy = productCopy;
    }

    public void setMembershipId(long membershipId) {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
