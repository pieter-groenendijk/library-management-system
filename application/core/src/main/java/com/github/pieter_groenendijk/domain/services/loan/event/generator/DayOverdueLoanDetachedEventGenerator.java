package com.github.pieter_groenendijk.domain.services.loan.event.generator;

import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.event.EventType;
import com.github.pieter_groenendijk.datasource.repositories.event.IEventRepository;

import java.time.LocalDateTime;

public class DayOverdueLoanDetachedEventGenerator extends DetachedLoanEventGenerator {
    public DayOverdueLoanDetachedEventGenerator(IEventRepository repository) {
        super(
            repository,
            EventType.DAY_OVERDUE_LOAN
        );
    }

    @Override
    protected LocalDateTime determineScheduledDateTime(Loan loan) {
        // start of tomorrow
        return LocalDateTime.now()
            .plusDays(1)
            .with(LocalDateTime.MIN);
    }
}
