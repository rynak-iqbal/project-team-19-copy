package seg3x02.pharmacysystem.application.usecases.agent

import seg3x02.pharmacysystem.application.dtos.agent.AgentRegistrationDto
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

interface RegisterAgent {
    fun register(request: AgentRegistrationDto): AgentId?
}
