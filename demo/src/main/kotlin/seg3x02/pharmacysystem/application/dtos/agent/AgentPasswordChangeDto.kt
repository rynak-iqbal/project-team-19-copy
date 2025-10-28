package seg3x02.pharmacysystem.application.dtos.agent

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

data class AgentPasswordChangeDto(
    val agentId: AgentId,
    val newPassword: String,
    val currentPassword: String? = null
)
