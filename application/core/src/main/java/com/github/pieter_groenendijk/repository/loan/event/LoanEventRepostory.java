package com.github.pieter_groenendijk.repository.loan.event;

import com.github.pieter_groenendijk.entity.Loan;
import com.github.pieter_groenendijk.entity.event.Event;
import com.github.pieter_groenendijk.entity.event.EventType;
import com.github.pieter_groenendijk.repository.fine.Repository;
import com.github.pieter_groenendijk.scheduling.TaskStatus;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;

import java.util.List;

@org.springframework.stereotype.Repository
public class LoanEventRepostory extends Repository implements ILoanEventRepostory {
    private final List<EventType> DUENESS_TYPES = List.of(
        EventType.OVERDUE_LOAN,
        EventType.ALMOST_OVERDUE_LOAN,
        EventType.DAY_OVERDUE_LOAN
    );

    public LoanEventRepostory(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void cancelDuenessEventsForLoan(Loan loan) throws Exception {
        super.performAtomicOperation((session) -> {
            Query query = session.createQuery(
                """
                update 
                   Event e 
                set 
                   e.status = :newStatus 
                where 
                   e.type in :types and
                   e.status = :oldStatus
                """,
                Event.class
            );

            query.setParameter("oldStatus", TaskStatus.SCHEDULED);
            query.setParameter("newStatus", TaskStatus.CANCELLED);
            query.setParameter("types", List.of(
                EventType.OVERDUE_LOAN,
                EventType.ALMOST_OVERDUE_LOAN,
                EventType.DAY_OVERDUE_LOAN
            ));

            query.executeUpdate();
        });
    }
}
