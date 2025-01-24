package com.github.pieter_groenendijk.repositories.notification;

import com.github.pieter_groenendijk.domain.entities.notification.Notification;
import com.github.pieter_groenendijk.repositories.scheduling.TaskRepository;
import com.github.pieter_groenendijk.domain.shared.scheduling.TaskStatus;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class NotificationRepository extends TaskRepository<Notification> implements INotificationRepository {
    public NotificationRepository(
        SessionFactory sessionFactory
    ) {
        super(sessionFactory);
    }

    // TODO: We could probably generalize this somehow
    @Override
    public List<Notification> retrieveUntil(LocalDateTime until) throws Exception {
        return super.performAtomicOperationReturning((session -> {
            return session.createQuery(
                    "select n from Notification as n where scheduledAt <= :until",
                    Notification.class
                )
                    .setParameter("until", until)
                    .getResultList();
        }));
    }

    @Override
    public List<Notification> retrieveRecentReceivedNotifications(Long accountId, int maxAmount) throws Exception {
        return super.performAtomicOperationReturning((session -> {
            return session.createQuery(
                """
                select n  
                from Notification as n   
                where 
                    n.account.id = :accountId and
                    n.status = :status   
                order by
                    n.scheduledAt asc
                """,
                Notification.class
            )
                .setParameter("accountId", accountId)
                .setParameter("status", TaskStatus.COMPLETED)
                .setMaxResults(maxAmount)
                .getResultList();
        }));
    }

    @Override
    public Optional<Notification> retrieve(Long notificationId) throws Exception {
        return super.get(Notification.class, notificationId);
    }
}
