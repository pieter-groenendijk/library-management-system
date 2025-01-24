package com.github.pieter_groenendijk.datasource.repositories;

import org.hibernate.Session;

public interface AtomicOperation {
    void run(Session session);
}
