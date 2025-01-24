package com.github.pieter_groenendijk.service.loan.event.generator;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.event.EventType;
import com.github.pieter_groenendijk.repository.event.IEventRepository;

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
