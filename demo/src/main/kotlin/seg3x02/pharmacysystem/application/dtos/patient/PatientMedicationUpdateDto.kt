package seg3x02.pharmacysystem.application.dtos.patient

import seg3x02.pharmacysystem.domain.patient.valueobjects.Medication
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId

data class PatientMedicationUpdateDto(
    val patientId: PatientId,
    val medications: List<Medication>
)
