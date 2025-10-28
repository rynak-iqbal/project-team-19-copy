package seg3x02.pharmacysystem.domain.prescription.valueobjects

import java.util.UUID

data class PrescriptionId(val value: String) {
    init {
        require(value.isNotBlank()) { "Prescription id cannot be blank." }
    }

    companion object {
        fun generate(): PrescriptionId {
            return PrescriptionId("RX-${UUID.randomUUID()}")
        }
    }
}

