package seg3x02.pharmacysystem.application.services

import seg3x02.pharmacysystem.domain.prescription.valueobjects.PrescriberInfo

interface PrescriberValidationService {
    fun validatePrescriber(licenseNumber: String): Boolean
    fun getPrescriberInfo(licenseNumber: String): PrescriberInfo
}
