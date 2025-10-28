package seg3x02.pharmacysystem.application.usecases.agent.implementation

import seg3x02.pharmacysystem.application.dtos.agent.AgentRegistrationDto
import seg3x02.pharmacysystem.application.usecases.agent.RegisterAgent
import seg3x02.pharmacysystem.domain.agent.facade.AgentFacade
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

class RegisterAgentImpl(
    private val agentFacade: AgentFacade
) : RegisterAgent {
    override fun register(request: AgentRegistrationDto): AgentId? {
        return agentFacade.registerAgent(
            username = request.username,
            profile = request.profile,
            role = request.role,
            password = request.password
        )
    }
}
