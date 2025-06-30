package com.example.medcentral.feature.auditpatient.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Patient {

    private UUID patientId;
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

    private UUID patientRegisteredAt; // FK to Hospital, optional
    private String patientAccessScope; // 'NATIONAL' or hospitalId

    private LocalDateTime patientCreatedAt;
    private LocalDateTime patientUpdatedAt;
}
