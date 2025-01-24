package com.github.pieter_groenendijk.domain.services.product;

import com.github.pieter_groenendijk.domain.services.event.emitting.EventEmitterPool;
import com.github.pieter_groenendijk.domain.services.event.listener.EventListener;

@Deprecated(
    forRemoval = true
)
public class EventPoolListener {
    protected final EventEmitterPool EVENT_EMITTER_POOL;

    public EventPoolListener(
        EventEmitterPool eventEmitterPool,
        EventListener<?>[] listeners
    ) {
        EVENT_EMITTER_POOL = eventEmitterPool;

        this.startListening(listeners);
    }

    private void startListening(EventListener<?>[] listeners) {
        for (EventListener<?> listener : listeners) {
            listener.attachTo(
                this.EVENT_EMITTER_POOL
            );
        }
    }
}
