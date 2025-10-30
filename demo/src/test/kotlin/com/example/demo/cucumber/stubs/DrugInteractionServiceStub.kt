package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.application.services.DrugInteractionService
import seg3x02.pharmacysystem.application.services.models.DrugInteraction
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN

class DrugInteractionServiceStub : DrugInteractionService {
    private val validDINs: MutableSet<DIN> = mutableSetOf()
    private val interactions: MutableList<DrugInteraction> = mutableListOf()

    override fun checkInteractions(patientMedications: List<String>, newDrug: String): List<DrugInteraction> {
        return interactions.filter { 
            (it.drug1 == newDrug && patientMedications.contains(it.drug2)) ||
            (it.drug2 == newDrug && patientMedications.contains(it.drug1))
        }
    }

    override fun validateDIN(din: DIN): Boolean {
        return validDINs.contains(din)
    }

    fun addValidDIN(din: DIN) {
        validDINs.add(din)
    }

    fun addInteraction(interaction: DrugInteraction) {
        interactions.add(interaction)
    }

    fun clear() {
        validDINs.clear()
        interactions.clear()
    }
}