package seg3x02.pharmacysystem.domain.patient.repositories

import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.patient.valueobjects.ProvincialHealthId

interface PatientRepository {
    fun save(patient: Patient): Patient
    fun findByProvincialHealthId(provincialHealthId: ProvincialHealthId): Patient?
    fun load(patientId: PatientId): Patient?
}
