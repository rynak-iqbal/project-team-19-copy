package seg3x02.pharmacysystem.domain.prescription.valueobjects

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import java.time.LocalDate
import java.time.LocalDateTime

data class PreparationDetails(
    val preparedBy: AgentId? = null,
    val preparedAt: LocalDateTime? = null,
    val verifiedBy: AgentId? = null,
    val verifiedAt: LocalDateTime? = null,
    val lotNumber: String? = null,
    val expiryDate: LocalDate? = null
)
