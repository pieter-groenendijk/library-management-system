package com.github.pieter_groenendijk.repository.fine;

import com.github.pieter_groenendijk.model.Account;
import com.github.pieter_groenendijk.model.fine.Fine;
import com.github.pieter_groenendijk.model.fine.FineBalance;
import com.github.pieter_groenendijk.model.fine.FineType;
import org.hibernate.*;


import java.util.List;
import java.util.Optional;

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

    public void payDebt(long accountId) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE Fine f SET f.isPaid = true WHERE f.account.id = :account";
            session.createQuery(hql)
                    .setParameter("account", accountId)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error in hibernate" + e.getMessage());
            e.printStackTrace();
        }finally {
            session.close();
        }
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
