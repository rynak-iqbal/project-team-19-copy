package seg3x02.pharmacysystem.domain.prescription.facade

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DosageInstructions
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.RefillInfo
import java.time.LocalDate

interface PrescriptionFacade {
    fun createPrescription(
        patientId: PatientId,
        drugInformation: DrugInformation,
        dosageInstructions: DosageInstructions,
        prescriberInfo: PrescriberInfo,
        refillInfo: RefillInfo
    ): PrescriptionId?

    fun preparePrescription(
        prescriptionId: PrescriptionId,
        preparedBy: AgentId,
        lotNumber: String,
        expiryDate: LocalDate
    ): Boolean

    fun verifyPrescription(prescriptionId: PrescriptionId, pharmacistId: AgentId): Boolean

    fun markAsPickedUp(prescriptionId: PrescriptionId): Boolean

    fun findPrescription(prescriptionId: PrescriptionId): Prescription?
}

