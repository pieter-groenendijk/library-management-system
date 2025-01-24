package com.github.pieter_groenendijk.repositories.event;

import com.github.pieter_groenendijk.domain.entities.event.Event;
import com.github.pieter_groenendijk.repositories.scheduling.TaskRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository extends TaskRepository<Event<?>> implements IEventRepository {
    public EventRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void store(Event<?> event) throws Exception {
        super.persist(event);
    }

    @Override
    public List<Event<?>> retrieveUntil(LocalDateTime until) throws Exception {
        return super.performAtomicOperationReturning((session -> {
            List<Event> uncastEvents = session.createQuery(
                    "select n from Event as n where scheduledAt <= :until",
                    Event.class
                )
                .setParameter("until", until)
                .getResultList();

            // TODO: We should probably remove the generics for the association so this is not pain.
            return uncastEvents
                .stream()
                .map(event -> (Event<?>) event)
                .collect(Collectors.toList());
        }));
    }
}
