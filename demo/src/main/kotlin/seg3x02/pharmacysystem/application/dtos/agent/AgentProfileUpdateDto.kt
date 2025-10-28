package seg3x02.pharmacysystem.application.dtos.agent

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo

data class AgentProfileUpdateDto(
    val agentId: AgentId,
    val profile: PersonalInfo
)
