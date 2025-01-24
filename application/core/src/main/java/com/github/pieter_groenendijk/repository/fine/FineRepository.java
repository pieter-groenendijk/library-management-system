package com.github.pieter_groenendijk.repository.fine;

import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.domain.entities.fine.Fine;
import com.github.pieter_groenendijk.domain.entities.fine.FineBalance;
import com.github.pieter_groenendijk.domain.entities.fine.FineType;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class FineRepository extends Repository implements IFineRepository {
    public FineRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<FineType> retrieveFineType(String title) throws Exception {
        return super.performAtomicOperationReturning((session -> {
            FineType type = session.createQuery(
                "select f from FineType f where f.title = :title",
                FineType.class
            )
                .setParameter("title", title)
                .getSingleResultOrNull();

            return Optional.ofNullable(type);
        }));
    }

    @Override
    public void store(Fine fine) throws Exception {
        super.persist(fine);
    }

    @Override
    public Optional<FineBalance> retrieveFineBalance(Account account) throws Exception {
        return super.get(FineBalance.class, account);
    }

    @Override
    public void payUnpaidFines(long accountId) throws Exception {
        super.performAtomicOperation((session -> {
            session.createMutationQuery(
                """
                update Fine f 
                set f.isPaid = true
                where 
                    f.account.id = :accountId and
                    f.isPaid = false
                """
            )
                .setParameter("accountId", accountId)
                .executeUpdate();
        }));
    }

    @Override
    public List<Fine> retrieveUnpaidFines(Long accountId) throws Exception {
        return super.performAtomicOperationReturning(session -> {
            return session.createQuery(
               """
                select f  
                from Fine as f  
                where f.isPaid = false and
                f.account.id = :accountId
                """,
                Fine.class
            )
               .setParameter("accountId", accountId)
               .getResultList();
        });
    }
}
