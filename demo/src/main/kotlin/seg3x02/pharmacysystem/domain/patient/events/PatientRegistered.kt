package seg3x02.pharmacysystem.domain.patient.events

import seg3x02.pharmacysystem.domain.common.DomainEvent
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId
import java.time.LocalDateTime
import java.util.UUID

data class PatientRegistered(
    val timestamp: LocalDateTime,
    val patientId: PatientId,
    val provincialHealthId: ProvincialHealthId
) : DomainEvent

