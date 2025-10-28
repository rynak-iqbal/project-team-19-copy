package seg3x02.pharmacysystem.domain.patient.factories.implementation

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.factories.PatientFactory
import seg3x02.pharmacysystem.domain.patient.valueobjects.InsuranceInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId
import java.time.LocalDateTime

class PatientFactoryImpl : PatientFactory {
    override fun registerPatient(
        personalInfo: PersonalInfo,
        provincialHealthId: ProvincialHealthId,
        medicalHistory: MedicalHistory,
        insuranceInfo: InsuranceInfo,
        consentSigned: Boolean
    ): Patient {
        return Patient.register(
            patientId = PatientId.generate(),
            provincialHealthId = provincialHealthId,
            personalInfo = personalInfo,
            medicalHistory = medicalHistory,
            insuranceInfo = insuranceInfo,
            consentSigned = consentSigned,
            dateRegistered = LocalDateTime.now()
        )
    }
}
