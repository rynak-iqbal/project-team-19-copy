package seg3x02.pharmacysystem.application.usecases.prescription.implementation

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionVerificationDto
import seg3x02.pharmacysystem.application.usecases.prescription.VerifyPrescription
import seg3x02.pharmacysystem.domain.prescription.facade.PrescriptionFacade

class VerifyPrescriptionImpl(
    private val prescriptionFacade: PrescriptionFacade
) : VerifyPrescription {
    override fun verify(request: PrescriptionVerificationDto): Boolean {
        return prescriptionFacade.verifyPrescription(
            prescriptionId = request.prescriptionId,
            pharmacistId = request.pharmacistId
        )
    }
}
