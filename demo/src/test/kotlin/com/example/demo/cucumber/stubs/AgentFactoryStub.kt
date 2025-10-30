package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.agent.entities.Agent
import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.factories.AgentFactory
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo
import java.time.LocalDateTime

class AgentFactoryStub : AgentFactory {
    override fun registerAgent(
        username: String,
        profile: PersonalInfo,
        role: Role,
        passwordHash: String
    ): Agent {
        return Agent.register(
            agentId = AgentId.generate(),
            username = username,
            personalInfo = profile,
            role = role,
            passwordHash = passwordHash,
            dateRegistered = LocalDateTime.now()
        )
    }
}