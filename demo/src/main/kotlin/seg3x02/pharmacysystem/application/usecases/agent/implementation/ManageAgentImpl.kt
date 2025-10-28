package seg3x02.pharmacysystem.application.usecases.agent.implementation

import seg3x02.pharmacysystem.application.dtos.agent.AgentPasswordChangeDto
import seg3x02.pharmacysystem.application.dtos.agent.AgentProfileUpdateDto
import seg3x02.pharmacysystem.application.usecases.agent.ManageAgent
import seg3x02.pharmacysystem.domain.agent.facade.AgentFacade

class ManageAgentImpl(
    private val agentFacade: AgentFacade
) : ManageAgent {
    override fun updateProfile(request: AgentProfileUpdateDto): Boolean {
        return agentFacade.updateProfile(request.agentId, request.profile)
    }

    override fun changePassword(request: AgentPasswordChangeDto): Boolean {
        return agentFacade.changePassword(request.agentId, request.newPassword, request.currentPassword)
    }
}
