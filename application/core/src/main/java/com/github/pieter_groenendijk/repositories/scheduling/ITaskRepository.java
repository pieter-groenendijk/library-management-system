package com.github.pieter_groenendijk.repositories.scheduling;

import com.github.pieter_groenendijk.domain.entities.scheduling.Task;
import com.github.pieter_groenendijk.domain.shared.scheduling.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ITaskRepository<T extends Task> {
    void updateStatus(Task task, TaskStatus status) throws Exception;

    List<T> retrieveUntil(LocalDateTime until) throws Exception;
}
