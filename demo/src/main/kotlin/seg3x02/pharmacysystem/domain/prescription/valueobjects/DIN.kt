package seg3x02.pharmacysystem.domain.prescription.valueobjects

private val DIN_REGEX = Regex("^[0-9]{8}$")

data class DIN(val number: String) {
    fun validate(): Boolean {
        return DIN_REGEX.matches(number)
    }
}

