package seg3x02.pharmacysystem.application.services

import seg3x02.pharmacysystem.domain.common.DomainEvent

interface DomainEventEmitter {
    fun emit(event: DomainEvent)
}
