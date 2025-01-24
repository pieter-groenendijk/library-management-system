package com.github.pieter_groenendijk.hibernate;

import com.github.pieter_groenendijk.hibernate.configuration.DefaultConfigurationFactory;
import org.hibernate.SessionFactory;

/**
 * Prefer the SessionFactory bean instead.
 */
@Deprecated(
    forRemoval = true
)
public class SessionFactoryFactory {
    public SessionFactory create() {
        return new DefaultConfigurationFactory()
            .create()
            .buildSessionFactory();
    }
}
