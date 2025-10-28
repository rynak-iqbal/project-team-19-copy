package seg3x02.pharmacysystem.domain.agent.repositories

import seg3x02.pharmacysystem.domain.agent.entities.Agent
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

interface AgentRepository {
    fun save(agent: Agent): Agent
    fun load(agentId: AgentId): Agent?
    fun findByUsername(username: String): Agent?
}
