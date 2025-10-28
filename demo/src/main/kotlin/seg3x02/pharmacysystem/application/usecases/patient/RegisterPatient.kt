package seg3x02.pharmacysystem.application.usecases.patient

import seg3x02.pharmacysystem.application.dtos.patient.PatientRegistrationDto
import seg3x02.pharmacysystem.domain.patient.valueobjects.PatientId

interface RegisterPatient {
    fun register(request: PatientRegistrationDto): PatientId?
}
