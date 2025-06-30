package com.example.medcentral.feature.auditpatient.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UpdatePatientRequest {

    @NotNull(message = "Patient ID is required for update")
    private UUID patientId;

    private String patientFirstName;

    private String patientLastName;

    private String patientOtherNames;

    @Past(message = "Date of birth must be in the past")
    private LocalDateTime patientDateOfBirth;

    private String patientGender;

    private String patientPhone;

    @Email(message = "Invalid email format")
    private String patientEmail;

    private String patientAddress;

    private String patientCountry;

    private String patientEmergencyContactName;

    private String patientEmergencyContactPhone;

    private String patientBloodGroup;

    private String patientGenotype;

    private UUID patientRegisteredAt;

    private String patientAccessScope;
}
