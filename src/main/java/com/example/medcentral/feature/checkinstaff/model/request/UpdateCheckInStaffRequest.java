package com.example.medcentral.feature.checkinstaff.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateCheckInStaffRequest {

    @NotNull(message = "Check-in staff ID is required for update")
    private UUID checkInStaffId;

    private UUID checkInId;

    private UUID staffId;

    private String checkInStaffRole;

//    @Pattern(regexp = "ACTIVE|REMOVED", message = "Status must be ACTIVE or REMOVED")
    private String checkInStaffStatus;
}
