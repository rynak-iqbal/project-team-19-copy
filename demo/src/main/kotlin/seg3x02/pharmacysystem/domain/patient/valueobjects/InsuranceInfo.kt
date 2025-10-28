package seg3x02.pharmacysystem.domain.patient.valueobjects

import java.time.LocalDate

data class InsuranceInfo(
    val provider: String,
    val policyNumber: String,
    val groupNumber: String,
    val effectiveDate: LocalDate
)
