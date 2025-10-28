# Patient Management Subdomain

**Type:** Core Domain

## Description

This subdomain manages all concepts related to patients and their medical information in the UYP-PMS system.

## Responsibilities

- Patient registration and profile creation
- Updating patient information
- Recording patient allergies and drug intolerances
- Managing current medications (prescription and non-prescription)
- Storing prescription medication insurance details
- Linking prescriptions to patients
- Maintaining provincial health identification numbers

## Key Concepts

- Patient profiles (names, address, gender, date of birth, language preference)
- Medical history (allergies, intolerances, current medications)
- Insurance information
- Health identification numbers

## Supported Use Cases

- **Register Patient** (Priority 4)
- **Update Patient** (Priority 5)
- Supports **Create Prescription** (Priority 1) by providing patient context

## Rationale

Patient management is central to the pharmacy's operations. Without accurate patient data, prescriptions cannot be safely created or managed. This domain forms the foundation for safe medication dispensing by maintaining critical patient information including allergies, current medications, and insurance details.

## Dependencies

- Interacts with Prescription Management for prescription-patient associations
- Requires Authentication & Security for access control
