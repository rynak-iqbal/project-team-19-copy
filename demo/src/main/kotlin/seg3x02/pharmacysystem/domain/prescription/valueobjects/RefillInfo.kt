package seg3x02.pharmacysystem.domain.prescription.valueobjects

import seg3x02.pharmacysystem.domain.prescription.enums.RefillType
import java.time.LocalDate

data class RefillInfo(
    val refillType: RefillType,
    val refillsRemaining: Int,
    val refillsAuthorized: Int,
    val lastRefillDate: LocalDate?
)
