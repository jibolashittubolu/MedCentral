package com.example.medcentral.feature.checkinstaff.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateCheckInStaffRequest {

    @NotNull(message = "Check-in ID is required")
    private UUID checkInId;

    @NotNull(message = "Staff ID is required")
    private UUID staffId;

    private String checkInStaffRole;

//    @Pattern(regexp = "ACTIVE|REMOVED", message = "Status must be ACTIVE or REMOVED")
    private String checkInStaffStatus; // Optional, defaults to ACTIVE
}
