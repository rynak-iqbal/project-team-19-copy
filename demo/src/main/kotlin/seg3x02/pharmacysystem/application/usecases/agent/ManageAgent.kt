package seg3x02.pharmacysystem.application.usecases.agent

import seg3x02.pharmacysystem.application.dtos.agent.AgentPasswordChangeDto
import seg3x02.pharmacysystem.application.dtos.agent.AgentProfileUpdateDto

interface ManageAgent {
    fun updateProfile(request: AgentProfileUpdateDto): Boolean
    fun changePassword(request: AgentPasswordChangeDto): Boolean
}
