package seg3x02.pharmacysystem.application.services

import seg3x02.pharmacysystem.application.services.models.DrugUsageReport
import seg3x02.pharmacysystem.application.services.models.PrescriptionStatistics
import seg3x02.pharmacysystem.application.services.models.TimePeriod
import seg3x02.pharmacysystem.domain.prescription.valueobjects.DIN
import java.time.LocalDate

interface ReportingService {
    fun generateDrugReport(din: DIN, startDate: LocalDate, endDate: LocalDate): DrugUsageReport
    fun getPrescriptionStatistics(timePeriod: TimePeriod): PrescriptionStatistics
}
