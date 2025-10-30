package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.application.services.PrescriptionValidationService
import seg3x02.pharmacysystem.application.services.models.ValidationResult
import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation

class PrescriptionValidationServiceStub : PrescriptionValidationService {
    private var validationResult: ValidationResult = ValidationResult(true)

    override fun validatePrescription(prescription: Prescription): ValidationResult {
        return validationResult
    }

    override fun checkPatientAllergies(patient: Patient, drug: DrugInformation): Boolean {
        return patient.medicalHistory.allergies.any { 
            it.substance.equals(drug.drugName, ignoreCase = true) 
        }
    }

    fun setValidationResult(result: ValidationResult) {
        validationResult = result
    }
}