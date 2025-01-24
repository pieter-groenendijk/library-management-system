package com.github.pieter_groenendijk.configuration.beans.scheduling;

import com.github.pieter_groenendijk.domain.shared.scheduling.TaskScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulingConfiguration {
    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskScheduler(2);
    }
}
