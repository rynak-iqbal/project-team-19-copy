package seg3x02.pharmacysystem.domain.agent.facade.implementation

import seg3x02.pharmacysystem.application.services.DomainEventEmitter
import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.events.AgentDeactivated
import seg3x02.pharmacysystem.domain.agent.events.AgentRegistered
import seg3x02.pharmacysystem.domain.agent.facade.AgentFacade
import seg3x02.pharmacysystem.domain.agent.factories.AgentFactory
import seg3x02.pharmacysystem.domain.agent.repositories.AgentRepository
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.agent.valueobjects.Credentials
import java.time.LocalDateTime
import java.util.UUID

class AgentFacadeImpl(
    private val agentRepository: AgentRepository,
    private val agentFactory: AgentFactory,
    private val eventEmitter: DomainEventEmitter
) : AgentFacade {

    override fun registerAgent(
        username: String,
        profile: PersonalInfo,
        role: Role,
        password: String
    ): AgentId? {
        val existing = agentRepository.findByUsername(username)
        if (existing != null) {
            return null
        }
        val passwordHash = Credentials.hashPassword(password)
        val agent = agentFactory.registerAgent(username, profile, role, passwordHash)
        agentRepository.save(agent)
        eventEmitter.emit(
            AgentRegistered(
                agentId = agent.agentId,
                username = agent.username,
                role = agent.role,
                timestamp = LocalDateTime.now(),
            )
        )
        return agent.agentId
    }

    override fun deactivateAgent(agentId: AgentId): Boolean {
        val agent = agentRepository.load(agentId) ?: return false
        agent.deactivate()
        agentRepository.save(agent)
        eventEmitter.emit(
            AgentDeactivated(
                agentId = agent.agentId,
                timestamp = LocalDateTime.now(),
            )
        )
        return true
    }

    override fun updateProfile(agentId: AgentId, profile: PersonalInfo): Boolean {
        val agent = agentRepository.load(agentId) ?: return false
        agent.updateProfile(profile)
        agentRepository.save(agent)
        return true
    }

    override fun changePassword(agentId: AgentId, newPassword: String, currentPassword: String?): Boolean {
        val agent = agentRepository.load(agentId) ?: return false
        if (currentPassword != null && !agent.validateCredentials(currentPassword)) {
            return false
        }
        val newHash = Credentials.hashPassword(newPassword)
        agent.changePasswordHash(newHash)
        agentRepository.save(agent)
        return true
    }
}
