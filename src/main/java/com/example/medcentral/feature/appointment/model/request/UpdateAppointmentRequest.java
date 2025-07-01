package com.example.medcentral.feature.appointment.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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
public class UpdateAppointmentRequest {

    @NotNull(message = "Appointment ID is required")
    private UUID appointmentId;

    private UUID appointmentStaffId;

    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDateTime;

    private String appointmentReason;

    private String appointmentStatus;
}
