package seg3x02.pharmacysystem.application.usecases.prescription

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionCreationDto
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

interface CreatePrescription {
    fun create(request: PrescriptionCreationDto): PrescriptionId?
}
