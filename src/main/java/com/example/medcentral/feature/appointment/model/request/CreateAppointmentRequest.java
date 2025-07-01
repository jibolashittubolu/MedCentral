package com.example.medcentral.feature.appointment.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
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
public class CreateAppointmentRequest {

    @NotNull(message = "Patient ID is required")
    private UUID appointmentPatientId;

    @NotNull(message = "Staff ID is required")
    private UUID appointmentStaffId;

    @NotNull(message = "Hospital ID is required")
    private UUID appointmentHospitalId;

    @Future(message = "Appointment date must be in the future")
    @NotNull(message = "Appointment Date and Time is required")
    private LocalDateTime appointmentDateTime;

    @NotBlank(message = "Appointment reason is required")
    private String appointmentReason;

}
