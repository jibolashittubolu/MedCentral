package com.example.medcentral.feature.checkin.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateCheckInRequest {

    @NotNull(message = "Check-in ID is required for update")
    private UUID checkInId;

    private UUID checkInPatientId;

    private UUID checkInHospitalId;

    private UUID checkInAppointmentId;

    private String checkInReason;

    private String checkInDiagnosis;

//    @Pattern(
//            regexp = "ACTIVE|COMPLETED|CANCELLED|ABANDONED",
//            message = "Status must be ACTIVE, COMPLETED, CANCELLED, or ABANDONED"
//    )
    private String checkInStatus;

    private String checkInPatientNotes;
}
