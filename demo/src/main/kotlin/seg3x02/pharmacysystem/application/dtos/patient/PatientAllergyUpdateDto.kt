package seg3x02.pharmacysystem.application.dtos.patient

import seg3x02.pharmacysystem.domain.patient.valueobjects.Allergy
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId

data class PatientAllergyUpdateDto(
    val patientId: PatientId,
    val allergy: Allergy
)
