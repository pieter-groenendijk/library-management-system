package com.github.pieter_groenendijk.domain.services.loan.fine.generator;

import com.github.pieter_groenendijk.domain.services.fine.type.FineTypeRegistry;
import com.github.pieter_groenendijk.domain.services.fine.type.StaticFineType;

public class DayOverdueLoanFineGenerator extends LoanFineGenerator {
    public DayOverdueLoanFineGenerator(FineTypeRegistry registry) {
        super(
            registry.fromStaticFineType(StaticFineType.DAY_OVERDUE_LENDING)
        );
    }
}
