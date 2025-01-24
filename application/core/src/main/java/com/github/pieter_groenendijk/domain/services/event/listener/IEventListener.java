package com.github.pieter_groenendijk.domain.services.event.listener;

public interface IEventListener<T> {
    void react(T context);
}
