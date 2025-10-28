package seg3x02.pharmacysystem.application.dtos.agent

import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo

data class AgentRegistrationDto(
    val username: String,
    val profile: PersonalInfo,
    val role: Role,
    val password: String
)
