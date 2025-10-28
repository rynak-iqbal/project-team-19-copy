package seg3x02.pharmacysystem.domain.prescription.events

import seg3x02.pharmacysystem.domain.common.DomainEvent
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN
import java.time.LocalDateTime

data class PrescriptionCreated(
    val din: DIN,
    val timestamp: LocalDateTime,
    val prescriptionId: PrescriptionId,
    val patientId: PatientId
) : DomainEvent

