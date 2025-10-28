package seg3x02.pharmacysystem.application.dtos.prescription

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import java.time.LocalDate

data class PrescriptionPreparationDto(
    val prescriptionId: PrescriptionId,
    val preparedBy: AgentId,
    val lotNumber: String,
    val expiryDate: LocalDate
)
