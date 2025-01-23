package com.github.pieter_groenendijk.model.DTO;

import java.util.List;

public class FineSummaryDTO {
    private List<FineDTO> fines;
    private long total;

    public List<FineDTO> getFines() {
        return fines;
    }

    public void setFines(List<FineDTO> fines) {
        this.fines = fines;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
