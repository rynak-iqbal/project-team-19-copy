package seg3x02.pharmacysystem.application.usecases.prescription.implementation

import seg3x02.pharmacysystem.application.dtos.prescription.PrescriptionCreationDto
import seg3x02.pharmacysystem.application.usecases.prescription.CreatePrescription
import seg3x02.pharmacysystem.domain.prescription.facade.PrescriptionFacade
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

class CreatePrescriptionImpl(
    private val prescriptionFacade: PrescriptionFacade
) : CreatePrescription {
    override fun create(request: PrescriptionCreationDto): PrescriptionId? {
        return prescriptionFacade.createPrescription(
            patientId = request.patientId,
            drugInformation = request.drugInformation,
            dosageInstructions = request.dosageInstructions,
            prescriberInfo = request.prescriberInfo,
            refillInfo = request.refillInfo
        )
    }
}
