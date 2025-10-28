package seg3x02.pharmacysystem.application.usecases.agent

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

interface DeactivateAgent {
    fun deactivate(agentId: AgentId): Boolean
}
