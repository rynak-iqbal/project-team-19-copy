package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.application.services.DomainEventEmitter
import seg3x02.pharmacysystem.domain.common.DomainEvent

class DomainEventEmitterStub : DomainEventEmitter {
    private val events: MutableList<DomainEvent> = mutableListOf()

    override fun emit(event: DomainEvent) {
        events.add(event)
    }

    fun getEvents(): List<DomainEvent> = events

    fun clear() {
        events.clear()
    }
}