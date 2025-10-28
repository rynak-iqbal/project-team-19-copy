package seg3x02.pharmacysystem.application.services.models

data class DrugInteraction(
    val drug1: String,
    val drug2: String,
    val severity: String,
    val description: String
)
