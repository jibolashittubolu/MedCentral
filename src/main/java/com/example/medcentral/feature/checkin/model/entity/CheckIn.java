package com.example.medcentral.feature.checkin.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CheckIn {

    private UUID checkInId;
    private UUID checkInPatientId;
    private UUID checkInHospitalId;
    private UUID checkInAppointmentId; // Optional

    private String checkInReason;
    private String checkInDiagnosis;

    // Enum or String depending on your preference
    private String checkInStatus; // ACTIVE, COMPLETED, CANCELLED, ABANDONED

    private String checkInPatientNotes;

    private LocalDateTime checkInCreatedAt;
    private LocalDateTime checkInUpdatedAt;
}
