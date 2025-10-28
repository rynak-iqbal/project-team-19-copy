package seg3x02.pharmacysystem.application.services.models

data class PrescriptionStatistics(
    val totalPrescriptions: Int,
    val uniquePatients: Int,
    val statusBreakdown: Map<String, Int>
)
