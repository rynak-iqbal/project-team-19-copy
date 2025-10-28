package seg3x02.pharmacysystem.domain.agent.valueobjects

import java.util.UUID

data class AgentId(val value: String) {
    init {
        require(value.isNotBlank()) { "Agent id cannot be blank." }
    }

    companion object {
        fun generate(): AgentId {
            return AgentId("AG-${UUID.randomUUID()}")
        }
    }
}

