package seg3x02.pharmacysystem.application.dtos.patient

import seg3x02.pharmacysystem.domain.patient.valueobjects.InsuranceInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId

data class PatientRegistrationDto(
    val personalInfo: PersonalInfo,
    val provincialHealthId: ProvincialHealthId,
    val medicalHistory: MedicalHistory,
    val insuranceInfo: InsuranceInfo,
    val consentSigned: Boolean
)
