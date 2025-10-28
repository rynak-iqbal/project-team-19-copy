package seg3x02.pharmacysystem.domain.prescription.valueobjects

import seg3x02.pharmacysystem.domain.common.valueobjects.Address

data class PrescriberInfo(
    val name: String,
    val licenseNumber: String,
    val title: String,
    val address: Address,
    val contactInfo: ContactInfo
) {
    fun validate(): Boolean {
        return name.isNotBlank() &&
            licenseNumber.isNotBlank() &&
            title.isNotBlank() &&
            address.street.isNotBlank() &&
            address.city.isNotBlank() &&
            address.province.isNotBlank() &&
            address.postalCode.isNotBlank() &&
            contactInfo.phone.isNotBlank()
    }
}
