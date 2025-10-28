package seg3x02.pharmacysystem.application.dtos.prescription

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId

data class PrescriptionVerificationDto(
    val prescriptionId: PrescriptionId,
    val pharmacistId: AgentId
)
