package com.example.medcentral.feature.appointment.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Appointment {

    private UUID appointmentId;

    private UUID appointmentPatientId;
    private UUID appointmentStaffId;
    private UUID appointmentHospitalId;

    private LocalDateTime appointmentDateTime;
    private String appointmentReason;

    private String appointmentStatus; // PENDING, CONFIRMED, RESCHEDULED, CANCELLED, COMPLETED

    private LocalDateTime appointmentCreatedAt;
    private LocalDateTime appointmentUpdatedAt;
}
