package seg3x02.pharmacysystem.domain.patient.entities

import seg3x02.pharmacysystem.domain.patient.valueobjects.Allergy
import seg3x02.pharmacysystem.domain.patient.valueobjects.InsuranceInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.Medication
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId
import java.time.LocalDateTime

class Patient(
    val patientId: PatientId,
    val provincialHealthId: ProvincialHealthId,
    personalInfo: PersonalInfo,
    medicalHistory: MedicalHistory,
    insuranceInfo: InsuranceInfo,
    consentSigned: Boolean,
    val dateRegistered: LocalDateTime
) {
    var personalInfo: PersonalInfo = personalInfo
        private set

    var medicalHistory: MedicalHistory = medicalHistory
        private set

    var insuranceInfo: InsuranceInfo = insuranceInfo
        private set

    var consentSigned: Boolean = consentSigned
        private set

    var lastUpdatedAt: LocalDateTime = dateRegistered
        private set

    fun updateProfile(updatedInfo: PersonalInfo) {
        personalInfo = updatedInfo
        touch()
    }

    fun addAllergy(allergy: Allergy) {
        medicalHistory = medicalHistory.addAllergy(allergy)
        touch()
    }

    fun updateMedications(medications: List<Medication>) {
        var updatedHistory = medicalHistory.copy(currentMedications = emptyList())
        medications.forEach { medication ->
            updatedHistory = updatedHistory.updateMedication(medication)
        }
        medicalHistory = updatedHistory
        touch()
    }

    fun validateConsent() {
        require(consentSigned) { "Patient must provide consent before registration." }
    }

    private fun touch() {
        lastUpdatedAt = LocalDateTime.now()
    }

    companion object {
        fun register(
            patientId: PatientId,
            provincialHealthId: ProvincialHealthId,
            personalInfo: PersonalInfo,
            medicalHistory: MedicalHistory,
            insuranceInfo: InsuranceInfo,
            consentSigned: Boolean,
            dateRegistered: LocalDateTime
        ): Patient {
            val patient = Patient(
                patientId = patientId,
                provincialHealthId = provincialHealthId,
                personalInfo = personalInfo,
                medicalHistory = medicalHistory,
                insuranceInfo = insuranceInfo,
                consentSigned = consentSigned,
                dateRegistered = dateRegistered
            )
            patient.validateConsent()
            return patient
        }
    }
}
