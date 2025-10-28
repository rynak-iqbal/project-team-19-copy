package seg3x02.pharmacysystem.domain.prescription.facade.implementation

import seg3x02.pharmacysystem.application.services.DomainEventEmitter
import seg3x02.pharmacysystem.application.services.DrugInteractionService
import seg3x02.pharmacysystem.application.services.PrescriptionValidationService
import seg3x02.pharmacysystem.application.services.PrescriberValidationService
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.patient.repositories.PatientRepository
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.events.MedicinePickedUp
import seg3x02.pharmacysystem.domain.prescription.events.PrescriptionCreated
import seg3x02.pharmacysystem.domain.prescription.events.PrescriptionPrepared
import seg3x02.pharmacysystem.domain.prescription.events.PrescriptionVerified
import seg3x02.pharmacysystem.domain.prescription.facade.PrescriptionFacade
import seg3x02.pharmacysystem.domain.prescription.factories.PrescriptionFactory
import seg3x02.pharmacysystem.domain.prescription.repositories.PrescriptionRepository
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DosageInstructions
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.RefillInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

class PrescriptionFacadeImpl(
    private val prescriptionRepository: PrescriptionRepository,
    private val patientRepository: PatientRepository,
    private val prescriptionFactory: PrescriptionFactory,
    private val drugInteractionService: DrugInteractionService,
    private val prescriptionValidationService: PrescriptionValidationService,
    private val prescriberValidationService: PrescriberValidationService,
    private val eventEmitter: DomainEventEmitter
) : PrescriptionFacade {

    override fun createPrescription(
        patientId: PatientId,
        drugInformation: DrugInformation,
        dosageInstructions: DosageInstructions,
        prescriberInfo: PrescriberInfo,
        refillInfo: RefillInfo
    ): PrescriptionId? {
        if (!prescriberValidationService.validatePrescriber(prescriberInfo.licenseNumber)) {
            return null
        }
        if (!drugInteractionService.validateDIN(drugInformation.din)) {
            return null
        }
        val patient = patientRepository.load(patientId) ?: return null
        if (prescriptionValidationService.checkPatientAllergies(patient, drugInformation)) {
            return null
        }
        val prescription = prescriptionFactory.createPrescription(
            patientId = patientId,
            drugInformation = drugInformation,
            dosageInstructions = dosageInstructions,
            prescriberInfo = prescriberInfo,
            refillInfo = refillInfo
        )
        val validationResult = prescriptionValidationService.validatePrescription(prescription)
        if (!validationResult.isValid) {
            return null
        }
        prescriptionRepository.save(prescription)
        eventEmitter.emit(
            PrescriptionCreated(
                din = prescription.drugInformation.din,
                timestamp = LocalDateTime.now(),
                prescriptionId = prescription.prescriptionId,
                patientId = patient.patientId
            )
        )
        return prescription.prescriptionId
    }

    override fun preparePrescription(
        prescriptionId: PrescriptionId,
        preparedBy: AgentId,
        lotNumber: String,
        expiryDate: LocalDate
    ): Boolean {
        val prescription = loadPrescription(prescriptionId) ?: return false
        val patient = patientRepository.load(prescription.patientId) ?: return false
        val currentMedications = patient.medicalHistory.currentMedications.map { it.name }
        val interactions = drugInteractionService.checkInteractions(currentMedications, prescription.drugInformation.drugName)
        if (interactions.isNotEmpty()) {
            return false
        }
        prescription.prepare(preparedBy, lotNumber, expiryDate)
        prescriptionRepository.save(prescription)
        eventEmitter.emit(
            PrescriptionPrepared(
                din = prescription.drugInformation.din,
                timestamp = LocalDateTime.now(),
                prescriptionId = prescription.prescriptionId,
                preparedBy = preparedBy
            )
        )
        return true
    }

    override fun verifyPrescription(prescriptionId: PrescriptionId, pharmacistId: AgentId): Boolean {
        val prescription = loadPrescription(prescriptionId) ?: return false
        try {
            prescription.verify(pharmacistId)
        } catch (ex: IllegalArgumentException) {
            return false
        } catch (ex: IllegalStateException) {
            return false
        }
        prescriptionRepository.save(prescription)
        eventEmitter.emit(
            PrescriptionVerified(
                din = prescription.drugInformation.din,
                timestamp = LocalDateTime.now(),
                prescriptionId = prescription.prescriptionId,
                verifiedBy = pharmacistId
            )
        )
        return true
    }

    override fun markAsPickedUp(prescriptionId: PrescriptionId): Boolean {
        val prescription = loadPrescription(prescriptionId) ?: return false
        try {
            prescription.markAsPickedUp()
        } catch (ex: IllegalArgumentException) {
            return false
        } catch (ex: IllegalStateException) {
            return false
        }
        prescriptionRepository.save(prescription)
        eventEmitter.emit(
            MedicinePickedUp(
                din = prescription.drugInformation.din,
                timestamp = LocalDateTime.now(),
                prescriptionId = prescription.prescriptionId,
                patientId = prescription.patientId
            )
        )
        return true
    }

    override fun findPrescription(prescriptionId: PrescriptionId): Prescription? {
        return loadPrescription(prescriptionId)
    }

    private fun loadPrescription(prescriptionId: PrescriptionId): Prescription? {
        return prescriptionRepository.load(prescriptionId)
    }
}
