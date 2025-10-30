package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.factories.PrescriptionFactory
import seg3x02.pharmacysystem.domain.prescription.valueobjects.*

class PrescriptionFactoryStub : PrescriptionFactory {
    override fun createPrescription(
        patientId: PatientId,
        drugInformation: DrugInformation,
        dosageInstructions: DosageInstructions,
        prescriberInfo: PrescriberInfo,
        refillInfo: RefillInfo
    ): Prescription {
        return Prescription.create(
            patientId = patientId,
            drugInformation = drugInformation,
            dosageInstructions = dosageInstructions,
            prescriberInfo = prescriberInfo,
            refillInfo = refillInfo
        )
    }
}