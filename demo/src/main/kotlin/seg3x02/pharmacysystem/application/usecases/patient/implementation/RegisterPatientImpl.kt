package seg3x02.pharmacysystem.application.usecases.patient.implementation

import seg3x02.pharmacysystem.application.dtos.patient.PatientRegistrationDto
import seg3x02.pharmacysystem.application.usecases.patient.RegisterPatient
import seg3x02.pharmacysystem.domain.patient.facade.PatientFacade
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId

class RegisterPatientImpl(
    private val patientFacade: PatientFacade
) : RegisterPatient {
    override fun register(request: PatientRegistrationDto): PatientId? {
        return patientFacade.registerPatient(
            personalInfo = request.personalInfo,
            provincialHealthId = request.provincialHealthId,
            medicalHistory = request.medicalHistory,
            insuranceInfo = request.insuranceInfo,
            consentSigned = request.consentSigned
        )
    }
}
