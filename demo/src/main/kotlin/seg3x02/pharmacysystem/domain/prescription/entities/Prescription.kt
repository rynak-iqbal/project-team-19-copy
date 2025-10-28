package seg3x02.pharmacysystem.domain.prescription.entities

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.patient.valueobjects.MedicalHistory
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.enums.PrescriptionStatus
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DosageInstructions
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PreparationDetails
import seg3x02.pharmacysystem.domain.prescription.valueobjects.RefillInfo
import java.time.LocalDate
import java.time.LocalDateTime

class Prescription(
    val prescriptionId: PrescriptionId,
    val patientId: PatientId,
    drugInformation: DrugInformation,
    dosageInstructions: DosageInstructions,
    prescriberInfo: PrescriberInfo,
    status: PrescriptionStatus,
    preparationDetails: PreparationDetails,
    refillInfo: RefillInfo,
    val dateCreated: LocalDateTime
) {
    var drugInformation: DrugInformation = drugInformation
        private set

    var dosageInstructions: DosageInstructions = dosageInstructions
        private set

    var prescriberInfo: PrescriberInfo = prescriberInfo
        private set

    var status: PrescriptionStatus = status
        private set

    var preparationDetails: PreparationDetails = preparationDetails
        private set

    var refillInfo: RefillInfo = refillInfo
        private set

    fun prepare(agentId: AgentId, lotNumber: String, expiryDate: LocalDate) {
        require(status == PrescriptionStatus.CREATED) { "Only prescriptions in CREATED status can be prepared." }
        preparationDetails = PreparationDetails(
            preparedBy = agentId,
            preparedAt = LocalDateTime.now(),
            verifiedBy = null,
            verifiedAt = null,
            lotNumber = lotNumber,
            expiryDate = expiryDate
        )
        status = PrescriptionStatus.PREPARED
    }

    fun verify(pharmacistId: AgentId) {
        require(status == PrescriptionStatus.PREPARED) { "Prescription must be prepared before verification." }
        require(preparationDetails.preparedBy != null) { "Prescription must be prepared before it can be verified." }
        preparationDetails = preparationDetails.copy(
            verifiedBy = pharmacistId,
            verifiedAt = LocalDateTime.now()
        )
        status = PrescriptionStatus.VERIFIED
    }

    fun markAsPickedUp() {
        require(status == PrescriptionStatus.VERIFIED) { "Prescription must be verified before pickup." }
        status = PrescriptionStatus.PICKED_UP
    }

    fun checkDrugInteractions(patientHistory: MedicalHistory): Boolean {
        return patientHistory.checkDrugInteractions(drugInformation.drugName)
    }

    companion object {
        fun create(
            patientId: PatientId,
            drugInformation: DrugInformation,
            dosageInstructions: DosageInstructions,
            prescriberInfo: PrescriberInfo,
            refillInfo: RefillInfo
        ): Prescription {
            return Prescription(
                prescriptionId = PrescriptionId.generate(),
                patientId = patientId,
                drugInformation = drugInformation,
                dosageInstructions = dosageInstructions,
                prescriberInfo = prescriberInfo,
                status = PrescriptionStatus.CREATED,
                preparationDetails = PreparationDetails(),
                refillInfo = refillInfo,
                dateCreated = LocalDateTime.now()
            )
        }
    }
}
