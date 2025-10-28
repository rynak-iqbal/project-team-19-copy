package seg3x02.pharmacysystem.domain.agent.events

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.common.DomainEvent
import java.time.LocalDateTime

data class AgentDeactivated(
    val agentId: AgentId,
    val timestamp: LocalDateTime,
) : DomainEvent

