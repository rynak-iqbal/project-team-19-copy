package seg3x02.pharmacysystem.domain.patient.valueobjects

data class MedicalHistory(
    val allergies: List<Allergy> = emptyList(),
    val currentMedications: List<Medication> = emptyList(),
    val medicalConditions: List<String> = emptyList()
) {
    fun addAllergy(allergy: Allergy): MedicalHistory {
        if (allergies.any { it.substance.equals(allergy.substance, ignoreCase = true) }) {
            return this
        }
        return copy(allergies = allergies + allergy)
    }

    fun updateMedication(medication: Medication): MedicalHistory {
        val withoutOld = currentMedications.filterNot { it.name.equals(medication.name, ignoreCase = true) }
        return copy(currentMedications = withoutOld + medication)
    }

    fun checkDrugInteractions(drugName: String): Boolean {
        return allergies.any { it.substance.equals(drugName, ignoreCase = true) } ||
            currentMedications.any { it.name.equals(drugName, ignoreCase = true) }
    }
}
