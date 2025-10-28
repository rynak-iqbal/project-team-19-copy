package seg3x02.pharmacysystem.domain.agent.entities

import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo
import seg3x02.pharmacysystem.domain.agent.valueobjects.Credentials
import java.time.LocalDateTime

class Agent(
    val agentId: AgentId,
    val username: String,
    personalInfo: PersonalInfo,
    role: Role,
    isActive: Boolean,
    credentials: Credentials,
    val dateRegistered: LocalDateTime
) {
    var personalInfo: PersonalInfo = personalInfo
        private set

    var role: Role = role
        private set

    var credentials: Credentials = credentials
        private set

    var active: Boolean = isActive
        private set

    var lastUpdatedAt: LocalDateTime = dateRegistered
        private set

    fun updateProfile(profile: PersonalInfo) {
        personalInfo = profile
        touch()
    }

    fun changePasswordHash(newPasswordHash: String) {
        credentials = credentials.copy(
            passwordHash = newPasswordHash,
            password = "",
            temporaryPassword = false,
            lastPasswordChange = LocalDateTime.now(),
            failedLoginAttempts = 0
        )
        touch()
    }

    fun validateCredentials(password: String): Boolean {
        return credentials.validatePassword(password)
    }

    fun deactivate() {
        active = false
        touch()
    }

    private fun touch() {
        lastUpdatedAt = LocalDateTime.now()
    }

    companion object {
        fun register(
            agentId: AgentId,
            username: String,
            personalInfo: PersonalInfo,
            role: Role,
            passwordHash: String,
            dateRegistered: LocalDateTime
        ): Agent {
            val credentials = Credentials(
                username = username,
                passwordHash = passwordHash,
                password = "",
                temporaryPassword = false,
                lastPasswordChange = dateRegistered,
                failedLoginAttempts = 0
            )
            return Agent(
                agentId = agentId,
                username = username,
                personalInfo = personalInfo,
                role = role,
                isActive = true,
                credentials = credentials,
                dateRegistered = dateRegistered
            )
        }
    }
}
