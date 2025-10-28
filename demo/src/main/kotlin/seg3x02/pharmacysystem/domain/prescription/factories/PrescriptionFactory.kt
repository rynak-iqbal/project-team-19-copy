package seg3x02.pharmacysystem.domain.prescription.factories

import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DosageInstructions
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.RefillInfo

interface PrescriptionFactory {
    fun createPrescription(
        patientId: PatientId,
        drugInformation: DrugInformation,
        dosageInstructions: DosageInstructions,
        prescriberInfo: PrescriberInfo,
        refillInfo: RefillInfo
    ): Prescription
}

