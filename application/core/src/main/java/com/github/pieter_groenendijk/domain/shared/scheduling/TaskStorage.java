package com.github.pieter_groenendijk.domain.shared.scheduling;

import com.github.pieter_groenendijk.domain.entities.scheduling.Task;

public interface TaskStorage<T extends Task> {
    void store(T task) throws Exception;
}
