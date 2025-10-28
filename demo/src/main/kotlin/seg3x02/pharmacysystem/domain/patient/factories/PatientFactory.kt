package seg3x02.pharmacysystem.domain.patient.factories

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.valueobjects.InsuranceInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId

interface PatientFactory {
    fun registerPatient(
        personalInfo: PersonalInfo,
        provincialHealthId: ProvincialHealthId,
        medicalHistory: MedicalHistory,
        insuranceInfo: InsuranceInfo,
        consentSigned: Boolean
    ): Patient
}

