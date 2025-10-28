package seg3x02.pharmacysystem.application.usecases.patient

import seg3x02.pharmacysystem.application.dtos.patient.PatientAllergyUpdateDto
import seg3x02.pharmacysystem.application.dtos.patient.PatientMedicationUpdateDto
import seg3x02.pharmacysystem.application.dtos.patient.PatientProfileUpdateDto

interface ManagePatientProfile {
    fun updateProfile(request: PatientProfileUpdateDto): Boolean
    fun addAllergy(request: PatientAllergyUpdateDto): Boolean
    fun updateMedications(request: PatientMedicationUpdateDto): Boolean
}
