package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.repositories.PrescriptionRepository
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

class PrescriptionRepositoryStub : PrescriptionRepository {
    private val prescriptions: MutableMap<String, Prescription> = mutableMapOf()

    override fun save(prescription: Prescription): Prescription {
        prescriptions[prescription.prescriptionId.value] = prescription
        return prescription
    }

    override fun load(prescriptionId: PrescriptionId): Prescription? {
        return prescriptions[prescriptionId.value]
    }

    override fun findByPatient(patientId: PatientId): List<Prescription> {
        return prescriptions.values.filter { it.patientId == patientId }
    }

    fun clear() {
        prescriptions.clear()
    }
}