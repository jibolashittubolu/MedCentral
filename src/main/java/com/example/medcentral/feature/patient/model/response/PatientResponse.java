package com.example.medcentral.feature.patient.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

    private String patientFirstName;

    private String patientLastName;

    private String patientOtherNames;

    private LocalDateTime patientDateOfBirth;

    private String patientGender;

    private String patientPhone;

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
