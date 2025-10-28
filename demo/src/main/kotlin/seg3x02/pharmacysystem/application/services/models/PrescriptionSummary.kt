package seg3x02.pharmacysystem.application.services.models
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import java.time.LocalDateTime

data class PrescriptionSummary(
    val prescriptionId: PrescriptionId,
    val patientId: PatientId,
    val din: DIN,
    val quantity: Int,
    val createdAt: LocalDateTime
)
