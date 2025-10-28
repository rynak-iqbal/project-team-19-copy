package seg3x02.pharmacysystem.application.dtos.prescription

import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DosageInstructions
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.RefillInfo

data class PrescriptionCreationDto(
    val patientId: PatientId,
    val drugInformation: DrugInformation,
    val dosageInstructions: DosageInstructions,
    val prescriberInfo: PrescriberInfo,
    val refillInfo: RefillInfo
)
