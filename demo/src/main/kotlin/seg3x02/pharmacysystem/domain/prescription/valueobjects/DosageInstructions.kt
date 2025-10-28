package seg3x02.pharmacysystem.domain.prescription.valueobjects

import seg3x02.pharmacysystem.domain.prescription.enums.AdministrationRoute

data class DosageInstructions(
    val dosage: String,
    val frequency: String,
    val route: AdministrationRoute,
    val duration: String,
    val specialInstructions: String
) {
    fun validate(): Boolean {
        return dosage.isNotBlank() && frequency.isNotBlank()
    }
}

