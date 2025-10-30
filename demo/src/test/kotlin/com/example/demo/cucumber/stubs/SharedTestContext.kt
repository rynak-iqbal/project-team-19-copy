package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

// Singleton to share test state across step definition classes
object SharedTestContext {
    // Shared repositories - single instance across all step definitions
    val agentRepository = AgentRepositoryStub()
    val patientRepository = PatientRepositoryStub()
    val prescriptionRepository = PrescriptionRepositoryStub()
    val agentFactory = AgentFactoryStub()
    val patientFactory = PatientFactoryStub()
    val prescriptionFactory = PrescriptionFactoryStub()
    val eventEmitter = DomainEventEmitterStub()
    val drugInteractionService = DrugInteractionServiceStub()
    val prescriptionValidationService = PrescriptionValidationServiceStub()
    val prescriberValidationService = PrescriberValidationServiceStub()

    // Shared state
    var currentAgentId: AgentId? = null
    var currentPatientId: PatientId? = null
    var currentPrescriptionId: PrescriptionId? = null

    fun reset() {
        // Clear all data
        agentRepository.clear()
        patientRepository.clear()
        prescriptionRepository.clear()
        drugInteractionService.clear()
        prescriberValidationService.clear()
        eventEmitter.clear()
        
        // Reset state
        currentAgentId = null
        currentPatientId = null
        currentPrescriptionId = null
    }
}