package com.github.pieter_groenendijk.domain.services.loan.event.generator;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.event.EventType;
import com.github.pieter_groenendijk.datasource.repositories.event.IEventRepository;

import java.time.LocalDateTime;

public class OverdueLoanDetachedEventGenerator extends DetachedLoanEventGenerator {
    public OverdueLoanDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.OVERDUE_LOAN
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        return loan
            .getReturnBy()
            .plusDays(1)
            .atStartOfDay();
    }
}
