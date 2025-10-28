package seg3x02.pharmacysystem.application.dtos.patient

import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo

data class PatientProfileUpdateDto(
    val patientId: PatientId,
    val personalInfo: PersonalInfo
)
