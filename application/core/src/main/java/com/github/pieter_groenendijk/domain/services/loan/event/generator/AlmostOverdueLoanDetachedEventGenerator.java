package com.github.pieter_groenendijk.domain.services.loan.event.generator;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.event.EventType;
import com.github.pieter_groenendijk.datasource.repositories.event.IEventRepository;

import java.time.LocalDateTime;

public class AlmostOverdueLoanDetachedEventGenerator extends DetachedLoanEventGenerator {
    public AlmostOverdueLoanDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.ALMOST_OVERDUE_LOAN
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        return loan
            .getReturnBy()
            .atStartOfDay(); // On the beginning of the last day, without getting a fine
    }
}
