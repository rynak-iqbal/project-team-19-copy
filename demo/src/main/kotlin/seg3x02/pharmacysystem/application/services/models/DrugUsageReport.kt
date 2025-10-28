package seg3x02.pharmacysystem.application.services.models

import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN

data class DrugUsageReport(
    val din: DIN,
    val period: TimePeriod,
    val totalPrescriptions: Int,
    val totalQuantity: Int,
    val prescriptions: List<PrescriptionSummary>
)
