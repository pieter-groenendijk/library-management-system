package com.github.pieter_groenendijk.datasource.repositories.event;

import com.github.pieter_groenendijk.domain.entities.event.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventRepository {
    void store(Event<?> event) throws Exception;
    List<Event<?>> retrieveUntil(LocalDateTime until) throws Exception; // TODO: Could generalize this in a separate interface; very similar for notifications.
}
