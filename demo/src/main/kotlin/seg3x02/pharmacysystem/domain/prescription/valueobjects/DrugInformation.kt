package seg3x02.pharmacysystem.domain.prescription.valueobjects

import seg3x02.pharmacysystem.domain.prescription.enums.DrugForm

data class DrugInformation(
    val din: DIN,
    val drugName: String,
    val strength: String,
    val form: DrugForm,
    val quantity: Int
) {
    fun validate(): Boolean {
        return din.validate() &&
            drugName.isNotBlank() &&
            strength.isNotBlank() &&
            quantity > 0
    }
}

