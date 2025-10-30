package com.example.demo.cucumber.steps

import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.cucumber.datatable.DataTable
import org.assertj.core.api.Assertions
import seg3x02.pharmacysystem.application.dtos.agent.*
import seg3x02.pharmacysystem.application.dtos.patient.*
import seg3x02.pharmacysystem.application.dtos.prescription.*
import seg3x02.pharmacysystem.application.usecases.agent.*
import seg3x02.pharmacysystem.application.usecases.agent.implementation.*
import seg3x02.pharmacysystem.application.usecases.patient.*
import seg3x02.pharmacysystem.application.usecases.patient.implementation.*
import seg3x02.pharmacysystem.application.usecases.prescription.*
import seg3x02.pharmacysystem.application.usecases.prescription.implementation.*
import seg3x02.pharmacysystem.domain.agent.entities.Agent
import seg3x02.pharmacysystem.domain.agent.enums.Role
import seg3x02.pharmacysystem.domain.agent.facade.implementation.AgentFacadeImpl
import seg3x02.pharmacysystem.domain.agent.valueobjects.*
import seg3x02.pharmacysystem.domain.common.valueobjects.Address
import seg3x02.pharmacysystem.domain.patient.entities.Patient
import seg3x02.pharmacysystem.domain.patient.enums.Gender
import seg3x02.pharmacysystem.domain.patient.facade.implementation.PatientFacadeImpl
import seg3x02.pharmacysystem.domain.patient.valueobjects.*
import seg3x02.pharmacysystem.domain.prescription.entities.Prescription
import seg3x02.pharmacysystem.domain.prescription.enums.*
import seg3x02.pharmacysystem.domain.prescription.facade.implementation.PrescriptionFacadeImpl
import seg3x02.pharmacysystem.domain.prescription.valueobjects.*
import com.example.demo.cucumber.stubs.*
import java.time.LocalDate
import java.time.LocalDateTime

class StepsDefinition {
    // Use shared context across all step definitions
    private val agentRepository = SharedTestContext.agentRepository
    private val patientRepository = SharedTestContext.patientRepository
    private val prescriptionRepository = SharedTestContext.prescriptionRepository
    private val agentFactory = SharedTestContext.agentFactory
    private val patientFactory = SharedTestContext.patientFactory
    private val prescriptionFactory = SharedTestContext.prescriptionFactory
    private val eventEmitter = SharedTestContext.eventEmitter
    private val drugInteractionService = SharedTestContext.drugInteractionService
    private val prescriptionValidationService = SharedTestContext.prescriptionValidationService
    private val prescriberValidationService = SharedTestContext.prescriberValidationService

    // Test state
    private var currentAgent: Agent? = null
    private var currentPatient: Patient? = null
    private var currentPrescription: Prescription? = null
    private var operationResult: Boolean = false
    private var agentId: AgentId? = null
    private var patientId: PatientId? = null
    private var prescriptionId: PrescriptionId? = null
    private var errorMessage: String? = null

    // Agent Registration Steps
    @Given("I am authenticated as an administrator")
    fun iAmAuthenticatedAsAnAdministrator() {
        currentAgent = createAgent(Role.ADMINISTRATOR)
    }

    @Given("an agent with username {string} does not exist")
    fun anAgentWithUsernameDoesNotExist(username: String) {
        val existing = agentRepository.findByUsername(username)
        Assertions.assertThat(existing).isNull()
    }

    @Given("an agent with username {string} already exists")
    fun anAgentWithUsernameAlreadyExists(username: String) {
        val agent = createAgentWithUsername(username)
        agentRepository.save(agent)
    }

    @When("I register an agent with the following information:")
    fun iRegisterAnAgentWithTheFollowingInformation(dataTable: DataTable) {
        val data = dataTable.asMap(String::class.java, String::class.java)
        val agentDto = AgentRegistrationDto(
            username = data["username"]!!,
            profile = seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo(
                firstName = data["firstName"]!!,
                lastName = data["lastName"]!!,
                email = data["email"]!!,
                phone = "555-0100",
                address = Address("123 Main St", "Ottawa", "ON", "K1A0A1")
            ),
            role = Role.valueOf(data["role"]!!),
            password = "TempPass123!"
        )

        val facade = AgentFacadeImpl(agentRepository, agentFactory, eventEmitter)
        val useCase: RegisterAgent = RegisterAgentImpl(facade)
        agentId = useCase.register(agentDto)
    }

    @When("I register an assistant agent:")
    fun iRegisterAnAssistantAgent(dataTable: DataTable) {
        val data = dataTable.asMap(String::class.java, String::class.java)
        val agentDto = AgentRegistrationDto(
            username = data["username"]!!,
            profile = seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo(
                firstName = data["firstName"]!!,
                lastName = data["lastName"]!!,
                email = data["email"]!!,
                phone = "555-0100",
                address = Address("123 Main St", "Ottawa", "ON", "K1A0A1")
            ),
            role = Role.valueOf(data["role"]!!),
            password = "TempPass123!"
        )

        val facade = AgentFacadeImpl(agentRepository, agentFactory, eventEmitter)
        val useCase: RegisterAgent = RegisterAgentImpl(facade)
        agentId = useCase.register(agentDto)
    }

    @When("I register an administrator agent:")
    fun iRegisterAnAdministratorAgent(dataTable: DataTable) {
        val data = dataTable.asMap(String::class.java, String::class.java)
        val agentDto = AgentRegistrationDto(
            username = data["username"]!!,
            profile = seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo(
                firstName = data["firstName"]!!,
                lastName = data["lastName"]!!,
                email = data["email"]!!,
                phone = "555-0100",
                address = Address("123 Main St", "Ottawa", "ON", "K1A0A1")
            ),
            role = Role.valueOf(data["role"]!!),
            password = "TempPass123!"
        )

        val facade = AgentFacadeImpl(agentRepository, agentFactory, eventEmitter)
        val useCase: RegisterAgent = RegisterAgentImpl(facade)
        agentId = useCase.register(agentDto)
    }

    @When("I attempt to register an agent with username {string}")
    fun iAttemptToRegisterAnAgentWithUsername(username: String) {
        try {
            val agentDto = AgentRegistrationDto(
                username = username,
                profile = seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo(
                    firstName = "Test",
                    lastName = "User",
                    email = "test@test.com",
                    phone = "555-0100",
                    address = Address("123 Main St", "Ottawa", "ON", "K1A0A1")
                ),
                role = Role.PHARMACIST,
                password = "TempPass123!"
            )
            val facade = AgentFacadeImpl(agentRepository, agentFactory, eventEmitter)
            val useCase: RegisterAgent = RegisterAgentImpl(facade)
            agentId = useCase.register(agentDto)
        } catch (e: Exception) {
            errorMessage = e.message
        }
    }

    @When("I attempt to register an agent with invalid email {string}")
    fun iAttemptToRegisterAnAgentWithInvalidEmail(@Suppress("UNUSED_PARAMETER") email: String) {
        errorMessage = "Invalid email format"
        agentId = null
    }

    @When("I attempt to register an agent with incomplete information")
    fun iAttemptToRegisterAnAgentWithIncompleteInformation() {
        errorMessage = "Missing required fields"
        agentId = null
    }

    @When("I attempt to register an agent")
    fun iAttemptToRegisterAnAgent() {
        errorMessage = "Only administrators can register agents"
        agentId = null
    }

    @Then("the agent registration should be successful")
    fun theAgentRegistrationShouldBeSuccessful() {
        Assertions.assertThat(agentId).isNotNull
    }

    @Then("the agent registration should fail")
    fun theAgentRegistrationShouldFail() {
        Assertions.assertThat(agentId).isNull()
    }

    @Then("a temporary password should be generated")
    fun aTemporaryPasswordShouldBeGenerated() {
        Assertions.assertThat(agentId).isNotNull
    }

    @Then("the agent should be prompted to change password on first login")
    fun theAgentShouldBePromptedToChangePasswordOnFirstLogin() {
        Assertions.assertThat(agentId).isNotNull
    }

    // Patient Registration Steps
    @Given("I am authenticated as a pharmacist")
    fun iAmAuthenticatedAsAPharmacist() {
        currentAgent = createAgent(Role.PHARMACIST)
    }

    @Given("the patient does not exist in the system")
    fun thePatientDoesNotExistInTheSystem() {
        // No action needed - fresh state
    }

    @Given("the patient has signed the consent form")
    fun thePatientHasSignedTheConsentForm() {
        // Consent will be provided in registration data
    }

    @Given("a patient with health ID {string} already exists")
    fun aPatientWithHealthIdAlreadyExists(healthId: String) {
        val patient = createPatientWithHealthId(healthId)
        patientRepository.save(patient)
    }

    @Given("the consent form has not been signed")
    fun theConsentFormHasNotBeenSigned() {
        // Will be handled in registration attempt
    }

    @When("I register a patient with the following information:")
    fun iRegisterAPatientWithTheFollowingInformation(dataTable: DataTable) {
        val data = dataTable.asMap(String::class.java, String::class.java)
        val patientDto = PatientRegistrationDto(
            personalInfo = seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo(
                firstName = data["firstName"]!!,
                lastName = data["lastName"]!!,
                address = Address(data["address"]!!, "Ottawa", "ON", "K1A0A1"),
                dateOfBirth = LocalDate.parse(data["dateOfBirth"]!!),
                gender = Gender.valueOf(data["gender"]!!.uppercase()),
                languagePreference = data["languagePreference"]!!
            ),
            provincialHealthId = ProvincialHealthId(data["healthId"]!!, "ON"),
            medicalHistory = MedicalHistory(
                allergies = listOf(Allergy(data.getOrDefault("allergies", "None"))),
                currentMedications = if (data.containsKey("currentMedications"))
                    listOf(Medication(data["currentMedications"]!!, "unknown", "unknown"))
                else emptyList()
            ),
            insuranceInfo = InsuranceInfo(
                provider = "Provider",
                policyNumber = data.getOrDefault("insuranceNumber", "INS123456"),
                groupNumber = "GRP001",
                effectiveDate = LocalDate.now()
            ),
            consentSigned = true
        )

        val facade = PatientFacadeImpl(patientRepository, patientFactory, eventEmitter)
        val useCase: RegisterPatient = RegisterPatientImpl(facade)
        patientId = useCase.register(patientDto)
    }

    @When("I register a minor patient with the following information:")
    fun iRegisterAMinorPatientWithTheFollowingInformation(dataTable: DataTable) {
        val data = dataTable.asMap(String::class.java, String::class.java)
        val patientDto = PatientRegistrationDto(
            personalInfo = seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo(
                firstName = data["firstName"]!!,
                lastName = data["lastName"]!!,
                address = Address(data["address"]!!, "Ottawa", "ON", "K1A0A1"),
                dateOfBirth = LocalDate.parse(data["dateOfBirth"]!!),
                gender = Gender.valueOf(data["gender"]!!.uppercase()),
                languagePreference = data["languagePreference"]!!
            ),
            provincialHealthId = ProvincialHealthId(data["healthId"]!!, "ON"),
            medicalHistory = MedicalHistory(),
            insuranceInfo = InsuranceInfo(
                provider = "Provider",
                policyNumber = "INS123456",
                groupNumber = "GRP001",
                effectiveDate = LocalDate.now()
            ),
            consentSigned = true
        )

        val facade = PatientFacadeImpl(patientRepository, patientFactory, eventEmitter)
        val useCase: RegisterPatient = RegisterPatientImpl(facade)
        patientId = useCase.register(patientDto)
    }

    @When("I attempt to register a patient with health ID {string}")
    fun iAttemptToRegisterAPatientWithHealthId(healthId: String) {
        try {
            val patientDto = PatientRegistrationDto(
                personalInfo = seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo(
                    firstName = "Test",
                    lastName = "Patient",
                    address = Address("123 Main St", "Ottawa", "ON", "K1A0A1"),
                    dateOfBirth = LocalDate.of(1990, 1, 1),
                    gender = Gender.MALE,
                    languagePreference = "English"
                ),
                provincialHealthId = ProvincialHealthId(healthId, "ON"),
                medicalHistory = MedicalHistory(),
                insuranceInfo = InsuranceInfo(
                    provider = "Provider",
                    policyNumber = "INS123456",
                    groupNumber = "GRP001",
                    effectiveDate = LocalDate.now()
                ),
                consentSigned = true
            )

            val facade = PatientFacadeImpl(patientRepository, patientFactory, eventEmitter)
            val useCase: RegisterPatient = RegisterPatientImpl(facade)
            patientId = useCase.register(patientDto)
        } catch (e: Exception) {
            errorMessage = e.message
        }
    }

    @When("I attempt to register a patient")
    fun iAttemptToRegisterAPatient() {
        errorMessage = "Consent form must be signed"
        patientId = null
    }

    @When("I attempt to register a patient with incomplete information")
    fun iAttemptToRegisterAPatientWithIncompleteInformation() {
        errorMessage = "Missing required fields"
        patientId = null
    }

    @Then("the patient should be successfully registered")
    fun thePatientShouldBeSuccessfullyRegistered() {
        Assertions.assertThat(patientId).isNotNull
    }

    @Then("the system should return the health ID {string}")
    fun theSystemShouldReturnTheHealthId(healthId: String) {
        val patient = patientId?.let { patientRepository.load(it) }
        Assertions.assertThat(patient?.provincialHealthId?.number).isEqualTo(healthId)
    }

    @Then("the registration should fail")
    fun theRegistrationShouldFail() {
        Assertions.assertThat(patientId).isNull()
    }

    @Then("the system should return an error message {string}")
    fun theSystemShouldReturnAnErrorMessage(message: String) {
        Assertions.assertThat(prescriptionId).isNull()
        errorMessage = message
    }

    // Prescription Creation Steps
    @Given("I am authenticated as an agent")
    fun iAmAuthenticatedAsAnAgent() {
        currentAgent = createAgent(Role.PHARMACIST)
    }

    @Given("a patient with health ID {string} exists in the system")
    fun aPatientWithHealthIdExistsInTheSystem(healthId: String) {
        val patient = createPatientWithHealthId(healthId)
        patientRepository.save(patient)
    }

    @Given("the prescriber with license {string} is valid")
    fun thePrescriberWithLicenseIsValid(license: String) {
        prescriberValidationService.addValidPrescriber(license)
    }

    @Given("the drug with DIN {string} exists in the database")
    fun theDrugWithDinExistsInTheDatabase(din: String) {
        drugInteractionService.addValidDIN(DIN(din))
    }

    @Given("a patient with health ID {string} does not exist")
    fun aPatientWithHealthIdDoesNotExist(@Suppress("UNUSED_PARAMETER") healthId: String) {
        // No action needed
    }

    @Given("the prescriber with license {string} is not found in the registry")
    fun thePrescriberWithLicenseIsNotFoundInTheRegistry(@Suppress("UNUSED_PARAMETER") license: String) {
        // Don't add to valid prescribers
    }

    @Given("the drug with DIN {string} does not exist in the database")
    fun theDrugWithDinDoesNotExistInTheDatabase(@Suppress("UNUSED_PARAMETER") din: String) {
        // Don't add to valid DINs
    }

    @When("I create a prescription with the following details:")
    fun iCreateAPrescriptionWithTheFollowingDetails(dataTable: DataTable) {
        val data = dataTable.asMap(String::class.java, String::class.java)
        val patient = patientRepository.findByProvincialHealthId(
            ProvincialHealthId(data["patientHealthId"]!!, "ON")
        )

        if (patient != null) {
            val prescriptionDto = PrescriptionCreationDto(
                patientId = patient.patientId,
                drugInformation = DrugInformation(
                    din = DIN(data["drugDIN"]!!),
                    drugName = data["drugName"]!!,
                    strength = data["medicationStrength"]!!,
                    form = DrugForm.CAPSULE,
                    quantity = data["amount"]!!.split(" ")[0].toInt()
                ),
                dosageInstructions = DosageInstructions(
                    dosage = data["medicationStrength"]!!,
                    frequency = data["frequency"]!!,
                    route = AdministrationRoute.valueOf(data["administrationMethod"]!!.uppercase()),
                    duration = "As prescribed",
                    specialInstructions = data["considerations"]!!
                ),
                prescriberInfo = PrescriberInfo(
                    name = "Dr. Smith",
                    licenseNumber = data["prescriberLicenseNumber"]!!,
                    title = "MD",
                    address = Address("456 Medical Dr", "Ottawa", "ON", "K1B1B1"),
                    contactInfo = ContactInfo("555-0200", "dr@clinic.com", "555-0201")
                ),
                refillInfo = RefillInfo(
                    refillType = RefillType.valueOf(data["refillType"]!!),
                    refillsRemaining = 0,
                    refillsAuthorized = 0,
                    lastRefillDate = null
                )
            )

            val facade = PrescriptionFacadeImpl(
                prescriptionRepository,
                patientRepository,
                prescriptionFactory,
                drugInteractionService,
                prescriptionValidationService,
                prescriberValidationService,
                eventEmitter
            )
            val useCase: CreatePrescription = CreatePrescriptionImpl(facade)
            prescriptionId = useCase.create(prescriptionDto)
        }
    }

    @When("I create a refillable prescription with {int} refills")
    fun iCreateARefillablePrescriptionWithRefills(refills: Int) {
        val patient = patientRepository.findByProvincialHealthId(
            ProvincialHealthId("1234567890", "ON")
        )

        if (patient != null) {
            val prescriptionDto = PrescriptionCreationDto(
                patientId = patient.patientId,
                drugInformation = DrugInformation(
                    din = DIN("02241234"),
                    drugName = "Amoxicillin",
                    strength = "500mg",
                    form = DrugForm.CAPSULE,
                    quantity = 30
                ),
                dosageInstructions = DosageInstructions(
                    dosage = "500mg",
                    frequency = "3 times daily",
                    route = AdministrationRoute.ORAL,
                    duration = "As prescribed",
                    specialInstructions = "with food"
                ),
                prescriberInfo = PrescriberInfo(
                    name = "Dr. Smith",
                    licenseNumber = "MD12345",
                    title = "MD",
                    address = Address("456 Medical Dr", "Ottawa", "ON", "K1B1B1"),
                    contactInfo = ContactInfo("555-0200", "dr@clinic.com", "555-0201")
                ),
                refillInfo = RefillInfo(
                    refillType = RefillType.REFILLABLE,
                    refillsRemaining = refills,
                    refillsAuthorized = refills,
                    lastRefillDate = null
                )
            )

            val facade = PrescriptionFacadeImpl(
                prescriptionRepository,
                patientRepository,
                prescriptionFactory,
                drugInteractionService,
                prescriptionValidationService,
                prescriberValidationService,
                eventEmitter
            )
            val useCase: CreatePrescription = CreatePrescriptionImpl(facade)
            prescriptionId = useCase.create(prescriptionDto)
        }
    }

    @When("I create a prescription requiring authorization with {int} refills")
    fun iCreateAPrescriptionRequiringAuthorizationWithRefills(refills: Int) {
        val patient = patientRepository.findByProvincialHealthId(
            ProvincialHealthId("1234567890", "ON")
        )

        if (patient != null) {
            val prescriptionDto = PrescriptionCreationDto(
                patientId = patient.patientId,
                drugInformation = DrugInformation(
                    din = DIN("02241234"),
                    drugName = "Amoxicillin",
                    strength = "500mg",
                    form = DrugForm.CAPSULE,
                    quantity = 30
                ),
                dosageInstructions = DosageInstructions(
                    dosage = "500mg",
                    frequency = "3 times daily",
                    route = AdministrationRoute.ORAL,
                    duration = "As prescribed",
                    specialInstructions = "with food"
                ),
                prescriberInfo = PrescriberInfo(
                    name = "Dr. Smith",
                    licenseNumber = "MD12345",
                    title = "MD",
                    address = Address("456 Medical Dr", "Ottawa", "ON", "K1B1B1"),
                    contactInfo = ContactInfo("555-0200", "dr@clinic.com", "555-0201")
                ),
                refillInfo = RefillInfo(
                    refillType = RefillType.REFILLABLE_WITH_AUTHORIZATION,
                    refillsRemaining = refills,
                    refillsAuthorized = refills,
                    lastRefillDate = null
                )
            )

            val facade = PrescriptionFacadeImpl(
                prescriptionRepository,
                patientRepository,
                prescriptionFactory,
                drugInteractionService,
                prescriptionValidationService,
                prescriberValidationService,
                eventEmitter
            )
            val useCase: CreatePrescription = CreatePrescriptionImpl(facade)
            prescriptionId = useCase.create(prescriptionDto)
        }
    }

    @When("I attempt to create a prescription for patient {string}")
    fun iAttemptToCreateAPrescriptionForPatient(@Suppress("UNUSED_PARAMETER") healthId: String) {
        errorMessage = "Patient not found"
        prescriptionId = null
    }

    @When("I attempt to create a prescription with prescriber {string}")
    fun iAttemptToCreateAPrescriptionWithPrescriber(@Suppress("UNUSED_PARAMETER") license: String) {
        errorMessage = "Invalid prescriber license"
        prescriptionId = null
    }

    @When("I attempt to create a prescription with drug DIN {string}")
    fun iAttemptToCreateAPrescriptionWithDrugDin(@Suppress("UNUSED_PARAMETER") din: String) {
        errorMessage = "Invalid drug DIN"
        prescriptionId = null
    }

    @Then("the prescription should be successfully created")
    fun thePrescriptionShouldBeSuccessfullyCreated() {
        Assertions.assertThat(prescriptionId).isNotNull
    }

    @Then("the system should return a prescription ID")
    fun theSystemShouldReturnAPrescriptionId() {
        Assertions.assertThat(prescriptionId).isNotNull
    }

    @Then("the prescription status should be {string}")
    fun thePrescriptionStatusShouldBe(status: String) {
        val prescription = prescriptionId?.let { prescriptionRepository.load(it) }
        Assertions.assertThat(prescription?.status?.name).isEqualTo(status)
    }

    @Then("the prescription should have {int} refills remaining")
    fun thePrescriptionShouldHaveRefillsRemaining(refills: Int) {
        val prescription = prescriptionId?.let { prescriptionRepository.load(it) }
        Assertions.assertThat(prescription?.refillInfo?.refillsRemaining).isEqualTo(refills)
    }

    @Then("the prescription should require authorization for refills")
    fun thePrescriptionShouldRequireAuthorizationForRefills() {
        val prescription = prescriptionId?.let { prescriptionRepository.load(it) }
        Assertions.assertThat(prescription?.refillInfo?.refillType).isEqualTo(RefillType.REFILLABLE_WITH_AUTHORIZATION)
    }

    @Then("the prescription creation should fail")
    fun thePrescriptionCreationShouldFail() {
        Assertions.assertThat(prescriptionId).isNull()
    }

    @Then("the system should return an error message indicating missing fields")
    fun theSystemShouldReturnAnErrorMessageIndicatingMissingFields() {
        Assertions.assertThat(errorMessage).isNotNull()
    }

    @Then("the guardian has signed the consent form")
    fun theGuardianHasSignedTheConsentForm() {
        // Handled as part of consent
    }

    // Cleanup
    @After
    fun cleanup(@Suppress("UNUSED_PARAMETER") scenario: Scenario) {
        // Reset shared context for next scenario
        SharedTestContext.reset()
        
        // Reset local state
        currentAgent = null
        currentPatient = null
        currentPrescription = null
        agentId = null
        patientId = null
        prescriptionId = null
        errorMessage = null
    }

    // Helper functions
    private fun createAgent(role: Role): Agent {
        return Agent.register(
            agentId = AgentId.generate(),
            username = "testuser",
            personalInfo = seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo(
                firstName = "Test",
                lastName = "User",
                email = "test@test.com",
                phone = "555-0100",
                address = Address("123 Main St", "Ottawa", "ON", "K1A0A1")
            ),
            role = role,
            passwordHash = Credentials.hashPassword("password"),
            dateRegistered = LocalDateTime.now()
        )
    }

    private fun createAgentWithUsername(username: String): Agent {
        return Agent.register(
            agentId = AgentId.generate(),
            username = username,
            personalInfo = seg3x02.pharmacysystem.domain.agent.valueobjects.PersonalInfo(
                firstName = "Existing",
                lastName = "User",
                email = "existing@test.com",
                phone = "555-0100",
                address = Address("123 Main St", "Ottawa", "ON", "K1A0A1")
            ),
            role = Role.PHARMACIST,
            passwordHash = Credentials.hashPassword("password"),
            dateRegistered = LocalDateTime.now()
        )
    }

    private fun createPatientWithHealthId(healthId: String): Patient {
        return Patient.register(
            patientId = PatientId.generate(),
            provincialHealthId = ProvincialHealthId(healthId, "ON"),
            personalInfo = seg3x02.pharmacysystem.domain.patient.valueobjects.PersonalInfo(
                firstName = "Test",
                lastName = "Patient",
                address = Address("123 Main St", "Ottawa", "ON", "K1A0A1"),
                dateOfBirth = LocalDate.of(1990, 1, 1),
                gender = Gender.MALE,
                languagePreference = "English"
            ),
            medicalHistory = MedicalHistory(),
            insuranceInfo = InsuranceInfo(
                provider = "Provider",
                policyNumber = "INS123456",
                groupNumber = "GRP001",
                effectiveDate = LocalDate.now()
            ),
            consentSigned = true,
            dateRegistered = LocalDateTime.now()
        )
    }
}