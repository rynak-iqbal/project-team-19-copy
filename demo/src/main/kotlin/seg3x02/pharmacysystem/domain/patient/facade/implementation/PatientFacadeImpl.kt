package seg3x02.pharmacysystem.domain.patient.facade.implementation

import seg3x02.pharmacysystem.application.services.DomainEventEmitter
import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.events.PatientRegistered
import seg3x02.pharmacysystem.domain.patient.events.PatientUpdated
import seg3x02.pharmacysystem.domain.patient.facade.PatientFacade
import seg3x02.pharmacysystem.domain.patient.factories.PatientFactory
import seg3x02.pharmacysystem.domain.patient.repositories.PatientRepository
import seg3x02.pharmacysystem.domain.patient.valueobjects.Allergy
import seg3x02.pharmacysystem.domain.patient.valueobjects.InsuranceInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.Medication
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId
import java.time.LocalDateTime

class PatientFacadeImpl(
    private val patientRepository: PatientRepository,
    private val patientFactory: PatientFactory,
    private val eventEmitter: DomainEventEmitter
) : PatientFacade {

    override fun registerPatient(
        personalInfo: PersonalInfo,
        provincialHealthId: ProvincialHealthId,
        medicalHistory: MedicalHistory,
        insuranceInfo: InsuranceInfo,
        consentSigned: Boolean
    ): PatientId {
        validate(personalInfo, provincialHealthId, medicalHistory, insuranceInfo, consentSigned)
        val existing = patientRepository.findByProvincialHealthId(provincialHealthId)
        if (existing != null) {
            return existing.patientId
        }
        val patient = patientFactory.registerPatient(
            personalInfo = personalInfo,
            provincialHealthId = provincialHealthId,
            medicalHistory = medicalHistory,
            insuranceInfo = insuranceInfo,
            consentSigned = consentSigned
        )
        val registeredEvent = PatientRegistered(
            timestamp = LocalDateTime.now(),
            patientId = patient.patientId,
            provincialHealthId = patient.provincialHealthId
        )
        eventEmitter.emit(registeredEvent)
        patientRepository.save(patient)
        return patient.patientId
    }

    override fun updateProfile(patientId: PatientId, personalInfo: PersonalInfo): Boolean {
        val patient = loadPatient(patientId) ?: return false
        patient.updateProfile(personalInfo)
        persistAndEmitUpdate(patient)
        return true
    }

    override fun addAllergy(patientId: PatientId, allergy: Allergy): Boolean {
        val patient = loadPatient(patientId) ?: return false
        patient.addAllergy(allergy)
        persistAndEmitUpdate(patient)
        return true
    }

    override fun updateMedications(patientId: PatientId, medications: List<Medication>): Boolean {
        val patient = loadPatient(patientId) ?: return false
        patient.updateMedications(medications)
        persistAndEmitUpdate(patient)
        return true
    }

    override fun findPatient(patientId: PatientId): Patient? {
        return patientRepository.load(patientId)
    }

    private fun loadPatient(patientId: PatientId): Patient? {
        return patientRepository.load(patientId)
    }

    private fun persistAndEmitUpdate(patient: Patient) {
        patientRepository.save(patient)
        val updatedEvent = PatientUpdated(
            timestamp = LocalDateTime.now(),
            patientId = patient.patientId
        )
        eventEmitter.emit(updatedEvent)
    }

    private fun validate(
        personalInfo: PersonalInfo,
        provincialHealthId: ProvincialHealthId,
        medicalHistory: MedicalHistory,
        insuranceInfo: InsuranceInfo,
        consentSigned: Boolean
    ) {
        require(consentSigned) { "Consent must be provided to register a patient." }
        require(personalInfo.firstName.isNotBlank()) { "First name is required." }
        require(personalInfo.lastName.isNotBlank()) { "Last name is required." }
        val address = personalInfo.address
        require(address.street.isNotBlank()) { "Street address is required." }
        require(address.city.isNotBlank()) { "City is required." }
        require(address.province.isNotBlank()) { "Province is required." }
        require(address.postalCode.isNotBlank()) { "Postal code is required." }
        require(provincialHealthId.validate()) { "Provincial health identifier is invalid." }
        require(insuranceInfo.provider.isNotBlank()) { "Insurance provider is required." }
        require(insuranceInfo.policyNumber.isNotBlank()) { "Insurance policy number is required." }
        require(insuranceInfo.groupNumber.isNotBlank()) { "Insurance group number is required." }
        // Medical history is supplied for completeness but no additional validation is documented.
    }
}
