package seg3x02.pharmacysystem.application.usecases.prescription

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionVerificationDto

interface VerifyPrescription {
    fun verify(request: PrescriptionVerificationDto): Boolean
}
