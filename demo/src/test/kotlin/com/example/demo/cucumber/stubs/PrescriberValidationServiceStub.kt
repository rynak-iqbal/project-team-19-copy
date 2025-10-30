package com.example.demo.cucumber.stubs

import seg3x02.pharmacysystem.application.services.PrescriberValidationService
import seg3x02.pharmacysystem.domain.common.valueobjects.Address
import seg3x02.pharmacysystem.domain.prescription.valueobjects.ContactInfo
import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo

class PrescriberValidationServiceStub : PrescriberValidationService {
    private val validPrescribers: MutableSet<String> = mutableSetOf()

    override fun validatePrescriber(licenseNumber: String): Boolean {
        return validPrescribers.contains(licenseNumber)
    }

    override fun getPrescriberInfo(licenseNumber: String): PrescriberInfo {
        return PrescriberInfo(
            name = "Dr. Smith",
            licenseNumber = licenseNumber,
            title = "MD",
            address = Address("456 Medical Dr", "Ottawa", "ON", "K1B1B1"),
            contactInfo = ContactInfo("555-0200", "dr@clinic.com", "555-0201")
        )
    }

    fun addValidPrescriber(licenseNumber: String) {
        validPrescribers.add(licenseNumber)
    }

    fun clear() {
        validPrescribers.clear()
    }
}