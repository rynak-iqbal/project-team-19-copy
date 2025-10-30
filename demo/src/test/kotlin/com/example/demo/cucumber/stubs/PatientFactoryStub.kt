package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.factories.PatientFactory
import seg3x02.pharmacysystem.domain.patient.valueobjects.*
import java.time.LocalDateTime

class PatientFactoryStub : PatientFactory {
    override fun registerPatient(
        personalInfo: PersonalInfo,
        provincialHealthId: ProvincialHealthId,
        medicalHistory: MedicalHistory,
        insuranceInfo: InsuranceInfo,
        consentSigned: Boolean
    ): Patient {
        return Patient.register(
            patientId = PatientId.generate(),
            provincialHealthId = provincialHealthId,
            personalInfo = personalInfo,
            medicalHistory = medicalHistory,
            insuranceInfo = insuranceInfo,
            consentSigned = consentSigned,
            dateRegistered = LocalDateTime.now()
        )
    }
}