package seg3x02.pharmacysystem.domain.patient.valueobjects

data class ProvincialHealthId(
    val number: String,
    val province: String
) {
    fun validate(): Boolean {
        return number.isNotBlank() && province.isNotBlank()
    }
}

