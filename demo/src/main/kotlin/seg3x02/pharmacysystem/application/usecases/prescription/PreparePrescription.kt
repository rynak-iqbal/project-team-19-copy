package seg3x02.pharmacysystem.application.usecases.prescription

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionPreparationDto

interface PreparePrescription {
    fun prepare(request: PrescriptionPreparationDto): Boolean
}
