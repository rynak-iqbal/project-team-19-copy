package seg3x02.pharmacysystem.domain.agent.events

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.common.DomainEvent
import seg3x02.pharmacysystem.domain.agent.enums.Role
import java.time.LocalDateTime

data class AgentRegistered(
    val agentId: AgentId,
    val username: String,
    val role: Role,
    val timestamp: LocalDateTime,
) : DomainEvent

