package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.repositories.PatientRepository
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId

class PatientRepositoryStub : PatientRepository {
    private val patients: MutableMap<String, Patient> = mutableMapOf()

    override fun save(patient: Patient): Patient {
        patients[patient.patientId.value] = patient
        return patient
    }

    override fun findByProvincialHealthId(provincialHealthId: ProvincialHealthId): Patient? {
        return patients.values.firstOrNull { 
            it.provincialHealthId.number == provincialHealthId.number &&
            it.provincialHealthId.province == provincialHealthId.province
        }
    }

    override fun load(patientId: PatientId): Patient? {
        return patients[patientId.value]
    }

    fun clear() {
        patients.clear()
    }
}