package seg3x02.pharmacysystem.application.usecases.prescription

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionPickupDto

interface MarkPrescriptionAsPickedUp {
    fun markAsPickedUp(request: PrescriptionPickupDto): Boolean
}
