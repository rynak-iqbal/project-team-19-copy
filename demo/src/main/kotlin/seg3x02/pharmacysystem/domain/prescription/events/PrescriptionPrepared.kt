package seg3x02.pharmacysystem.domain.prescription.events

import seg3x02.pharmacysystem.domain.agent.valueobjects.AgentId
import seg3x02.pharmacysystem.domain.common.DomainEvent
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriptionId
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN
import java.time.LocalDateTime

data class PrescriptionPrepared(
    val din: DIN,
    val timestamp: LocalDateTime,
    val prescriptionId: PrescriptionId,
    val preparedBy: AgentId
) : DomainEvent

