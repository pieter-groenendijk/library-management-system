package com.github.pieter_groenendijk.model.fine;

import java.util.List;

public class FineSummary {
    private List<Fine> fines;
    private long total;

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
