package com.github.pieter_groenendijk.configuration.beans;

import com.github.pieter_groenendijk.hibernate.configuration.DefaultConfigurationFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    @Bean
    public SessionFactory sessionFactory() {
        return new DefaultConfigurationFactory()
            .create()
            .buildSessionFactory();
    }
}
