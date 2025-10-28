package seg3x02.pharmacysystem.domain.patient.valueobjects

data class Allergy(
    val substance: String,
    val reaction: String? = null,
    val severity: String? = null
)
