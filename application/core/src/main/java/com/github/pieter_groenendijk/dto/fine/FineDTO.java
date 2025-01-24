package com.github.pieter_groenendijk.dto.fine;

import java.time.LocalDateTime;

public class FineDTO {
    private Long fineId;
    private String fineType;
    private Long amountInCents;
    private LocalDateTime declaredOn;

    public FineDTO() {}

    public Long getFineId() {
        return fineId;
    }

    public void setFineId(Long fineId) {
        this.fineId = fineId;
    }

    public String getFineType() {
        return fineType;
    }

    public void setFineType(String fineType) {
        this.fineType = fineType;
    }

    public Long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(Long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public LocalDateTime getDeclaredOn() {
        return declaredOn;
    }

    public void setDeclaredOn(LocalDateTime declaredOn) {
        this.declaredOn = declaredOn;
    }
}
