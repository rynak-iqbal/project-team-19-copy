package seg3x02.pharmacysystem.application.services

import seg3x02.pharmacysystem.application.services.models.DrugInteraction
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN

interface DrugInteractionService {
    fun checkInteractions(patientMedications: List<String>, newDrug: String): List<DrugInteraction>
    fun validateDIN(din: DIN): Boolean
}
