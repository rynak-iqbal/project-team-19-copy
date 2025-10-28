package seg3x02.pharmacysystem.application.services

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DrugInformation
import seg3x02.pharmacysystem.application.services.models.ValidationResult

interface PrescriptionValidationService {
    fun validatePrescription(prescription: Prescription): ValidationResult
    fun checkPatientAllergies(patient: Patient, drug: DrugInformation): Boolean
}
