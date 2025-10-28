package seg3x02.pharmacysystem.domain.patient.events

import seg3x02.pharmacysystem.domain.common.DomainEvent
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import java.time.LocalDateTime

data class PatientUpdated(
    val timestamp: LocalDateTime,
    val patientId: PatientId
) : DomainEvent

