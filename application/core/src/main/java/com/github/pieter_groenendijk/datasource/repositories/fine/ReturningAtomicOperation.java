package com.github.pieter_groenendijk.datasource.repositories.fine;

import org.hibernate.Session;

public interface ReturningAtomicOperation<Result> {
    Result run(Session session);
}
