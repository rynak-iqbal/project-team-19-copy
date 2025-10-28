package seg3x02.pharmacysystem.application.usecases.agent.implementation

import seg3x02.pharmacysystem.application.usecases.agent.DeactivateAgent
import seg3x02.pharmacysystem.domain.agent.facade.AgentFacade
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

class DeactivateAgentImpl(
    private val agentFacade: AgentFacade
) : DeactivateAgent {
    override fun deactivate(agentId: AgentId): Boolean {
        return agentFacade.deactivateAgent(agentId)
    }
}
