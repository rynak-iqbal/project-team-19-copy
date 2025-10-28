package seg3x02.pharmacysystem.domain.patient.valueobjects

import java.util.UUID

data class PatientId(val value: String) {
    init {
        require(value.isNotBlank()) { "Patient id cannot be blank." }
    }

    companion object {
        fun generate(): PatientId {
            return PatientId("PAT-${UUID.randomUUID()}")
        }
    }
}

