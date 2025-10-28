package seg3x02.pharmacysystem.application.dtos.prescription

import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

data class PrescriptionPickupDto(
    val prescriptionId: PrescriptionId
)
