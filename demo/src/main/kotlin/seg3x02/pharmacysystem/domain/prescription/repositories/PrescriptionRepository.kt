package seg3x02.pharmacysystem.domain.prescription.repositories

import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

interface PrescriptionRepository {
    fun save(prescription: Prescription): Prescription
    fun load(prescriptionId: PrescriptionId): Prescription?
    fun findByPatient(patientId: seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId): List<Prescription>
}
