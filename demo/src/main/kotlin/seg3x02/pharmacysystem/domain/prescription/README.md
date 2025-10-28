# Prescription Management Subdomain

**Type:** Core Domain

## Description

This subdomain manages the complete lifecycle of prescriptions from creation through preparation, verification, and patient pickup.

## Responsibilities

- Creating new prescriptions with detailed medication information
- Tracking prescription status through workflow stages
- Managing prescription refills and re-authorization requirements
- Recording prescription preparation details (lot numbers, expiry dates)
- Pharmacist verification and clinical checks
- Patient pickup tracking and counseling documentation
- Validating prescribers against professional registries
- Validating medications against Health Canada's Drug Product Database

## Key Concepts

- Prescription lifecycle (created → prepared → verified → picked-up)
- Medication details (DIN, strength, dosage, administration method, frequency)
- Refill policies (refillable, refillable with re-authorization, non-refillable)
- Prescriber validation
- Drug validation and interactions
- Lot tracking and expiry management

## Supported Use Cases

- **Create Prescription** (Priority 1)
- **Prepare Prescription Fill** (Priority 2)
- **Pick up Medicine** (Priority 3)

## Rationale

Prescription management is the primary objective of the UYP-PMS system. It supports critical workflows from prescription entry through medication dispensing. As the pharmacy's main business process and the reason for the system's existence, this is the most important subdomain. It ensures medication safety through validation, tracking, and verification processes.

## Dependencies

- Requires Patient Management for patient information
- Requires Agent Management for tracking who performs each step
- Integrates with external PrescriberRegistry and DrugProductDatabase
- Requires Authentication & Security for access control
