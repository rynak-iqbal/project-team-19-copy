package seg3x02.pharmacysystem.domain.agent.facade

import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo

interface AgentFacade {
    fun registerAgent(username: String, profile: PersonalInfo, role: Role, password: String): AgentId?
    fun deactivateAgent(agentId: AgentId): Boolean
    fun updateProfile(agentId: AgentId, profile: PersonalInfo): Boolean
    fun changePassword(agentId: AgentId, newPassword: String, currentPassword: String? = null): Boolean
}
