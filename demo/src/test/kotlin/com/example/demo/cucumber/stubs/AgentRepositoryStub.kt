package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.agent.entities.Agent
import seg3x02.pharmacysystem.domain.agent.repositories.AgentRepository
import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId

class AgentRepositoryStub : AgentRepository {
    private val agents: MutableMap<String, Agent> = mutableMapOf()

    override fun save(agent: Agent): Agent {
        agents[agent.agentId.value] = agent
        return agent
    }

    override fun load(agentId: AgentId): Agent? {
        return agents[agentId.value]
    }

    override fun findByUsername(username: String): Agent? {
        return agents.values.firstOrNull { it.username == username }
    }

    fun clear() {
        agents.clear()
    }
}