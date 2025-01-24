package com.github.pieter_groenendijk.configuration.beans.scheduling;

import com.github.pieter_groenendijk.entity.event.Event;
import com.github.pieter_groenendijk.repository.event.EventRepository;
import com.github.pieter_groenendijk.repository.scheduling.ITaskRepository;
import com.github.pieter_groenendijk.scheduling.TaskScheduler;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulingConfiguration {
    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskScheduler(2);
    }

//    @Bean
//    public ITaskRepository<Event<?>> eventTaskRepository(
//        SessionFactory sessionFactory
//    ) {
//        return (ITaskRepository<Event<?>>) new EventRepository(sessionFactory);
//    }
}
