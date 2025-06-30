package com.example.medcentral.feature.checkinstaff.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CheckInStaff {

    private UUID checkInStaffId;
    private UUID checkInId;
    private UUID staffId;

    private String checkInStaffRole;       // e.g., Doctor, Nurse, Assistant
    private String checkInStaffStatus;     // ACTIVE, REMOVED

    private LocalDateTime checkInStaffCreatedAt;
    private LocalDateTime checkInStaffUpdatedAt;
}
