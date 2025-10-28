package seg3x02.pharmacysystem.domain.prescription.factories.implementation

import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.factories.PrescriptionFactory
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DosageInstructions
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.RefillInfo

class PrescriptionFactoryImpl : PrescriptionFactory {
    override fun createPrescription(
        patientId: PatientId,
        drugInformation: DrugInformation,
        dosageInstructions: DosageInstructions,
        prescriberInfo: PrescriberInfo,
        refillInfo: RefillInfo
    ): Prescription {
        require(drugInformation.validate()) { "Invalid drug information supplied." }
        require(dosageInstructions.validate()) { "Invalid dosage instructions supplied." }
        require(prescriberInfo.validate()) { "Invalid prescriber information supplied." }
        return Prescription.create(
            patientId = patientId,
            drugInformation = drugInformation,
            dosageInstructions = dosageInstructions,
            prescriberInfo = prescriberInfo,
            refillInfo = refillInfo
        )
    }
}
