package seg3x02.pharmacysystem.application.usecases.prescription.implementation

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionPickupDto
import seg3x02.pharmacysystem.application.usecases.prescription.MarkPrescriptionAsPickedUp
import seg3x02.pharmacysystem.domain.prescription.facade.PrescriptionFacade

class MarkPrescriptionAsPickedUpImpl(
    private val prescriptionFacade: PrescriptionFacade
) : MarkPrescriptionAsPickedUp {
    override fun markAsPickedUp(request: PrescriptionPickupDto): Boolean {
        return prescriptionFacade.markAsPickedUp(request.prescriptionId)
    }
}
