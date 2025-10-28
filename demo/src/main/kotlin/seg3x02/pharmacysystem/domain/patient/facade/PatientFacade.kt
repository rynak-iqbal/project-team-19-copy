package seg3x02.pharmacysystem.domain.patient.facade

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.valueobjects.Allergy
import seg3x02.pharmacysystem.domain.patient.valueobjects.InsuranceInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.Medication
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId

interface PatientFacade {
    fun registerPatient(
        personalInfo: PersonalInfo,
        provincialHealthId: ProvincialHealthId,
        medicalHistory: MedicalHistory,
        insuranceInfo: InsuranceInfo,
        consentSigned: Boolean
    ): PatientId

    fun updateProfile(patientId: PatientId, personalInfo: PersonalInfo): Boolean

    fun addAllergy(patientId: PatientId, allergy: Allergy): Boolean

    fun updateMedications(patientId: PatientId, medications: List<Medication>): Boolean

    fun findPatient(patientId: PatientId): Patient?
}
