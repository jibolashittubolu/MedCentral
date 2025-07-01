package com.example.medcentral.feature.checkin.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateCheckInRequest {

    @NotNull(message = "Patient ID is required")
    private UUID checkInPatientId;

    @NotNull(message = "Hospital ID is required")
    private UUID checkInHospitalId;

    private UUID checkInAppointmentId; // Optional

    @NotBlank(message = "Check-in reason is required")
    private String checkInReason;

//    @Pattern(
//            regexp = "ACTIVE|COMPLETED|CANCELLED|ABANDONED",
//            message = "Status must be ACTIVE, COMPLETED, CANCELLED, or ABANDONED"
//    )
    private String checkInStatus; // Optional, defaults to ACTIVE

    private String checkInPatientNotes;
}
