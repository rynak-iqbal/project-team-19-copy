package seg3x02.pharmacysystem.domain.agent.factories

import seg3x02.pharmacysystem.domain.agent.entities.Agent
import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo

interface AgentFactory {
    fun registerAgent(
        username: String,
        profile: PersonalInfo,
        role: Role,
        passwordHash: String
    ): Agent
}
