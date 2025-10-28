package seg3x02.pharmacysystem.application.usecases.patient.implementation

import seg3x02.pharmacysystem.application.dtos.patient.PatientAllergyUpdateDto
import seg3x02.pharmacysystem.application.dtos.patient.PatientMedicationUpdateDto
import seg3x02.pharmacysystem.application.dtos.patient.PatientProfileUpdateDto
import seg3x02.pharmacysystem.application.usecases.patient.ManagePatientProfile
import seg3x02.pharmacysystem.domain.patient.facade.PatientFacade

class ManagePatientProfileImpl(
    private val patientFacade: PatientFacade
) : ManagePatientProfile {
    override fun updateProfile(request: PatientProfileUpdateDto): Boolean {
        return patientFacade.updateProfile(request.patientId, request.personalInfo)
    }

    override fun addAllergy(request: PatientAllergyUpdateDto): Boolean {
        return patientFacade.addAllergy(request.patientId, request.allergy)
    }

    override fun updateMedications(request: PatientMedicationUpdateDto): Boolean {
        return patientFacade.updateMedications(request.patientId, request.medications)
    }
}
