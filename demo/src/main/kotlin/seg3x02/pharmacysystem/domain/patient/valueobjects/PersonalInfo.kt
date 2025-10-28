package seg3x02.pharmacysystem.domain.patient.valueobjects

import seg3x02.pharmacysystem.domain.common.valueobjects.Address
import seg3x02.pharmacysystem.domain.patient.enums.Gender
import java.time.LocalDate

data class PersonalInfo(
    val firstName: String,
    val lastName: String,
    val address: Address,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val languagePreference: String
)
