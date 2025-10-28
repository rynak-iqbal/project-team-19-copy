package seg3x02.pharmacysystem.application.usecases.prescription.implementation

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionPreparationDto
import seg3x02.pharmacysystem.application.usecases.prescription.PreparePrescription
import seg3x02.pharmacysystem.domain.prescription.facade.PrescriptionFacade

class PreparePrescriptionImpl(
    private val prescriptionFacade: PrescriptionFacade
) : PreparePrescription {
    override fun prepare(request: PrescriptionPreparationDto): Boolean {
        return prescriptionFacade.preparePrescription(
            prescriptionId = request.prescriptionId,
            preparedBy = request.preparedBy,
            lotNumber = request.lotNumber,
            expiryDate = request.expiryDate
        )
    }
}
