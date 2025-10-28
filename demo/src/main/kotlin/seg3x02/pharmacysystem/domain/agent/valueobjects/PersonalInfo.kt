package seg3x02.pharmacysystem.domain.agent.valueobjects

import seg3x02.pharmacysystem.domain.common.valueobjects.Address

data class PersonalInfo(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val address: Address
)
