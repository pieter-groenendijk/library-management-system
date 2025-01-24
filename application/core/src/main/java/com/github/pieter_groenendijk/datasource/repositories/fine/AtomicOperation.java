package com.github.pieter_groenendijk.datasource.repositories.fine;

import org.hibernate.Session;

public interface AtomicOperation {
    void run(Session session);
}
