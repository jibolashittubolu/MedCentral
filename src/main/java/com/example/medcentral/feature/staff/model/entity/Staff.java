package com.example.medcentral.feature.staff.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Staff {

    private UUID staffId;

    private String staffFirstName;
    private String staffLastName;
    private String staffOtherNames;

    private LocalDateTime staffDateOfBirth;
    private String staffGender;

    private String staffPhone;
    private String staffEmail;
    private String staffAddress;

    private String staffRole;        // e.g., Doctor, Nurse
    private String staffDepartment;

    private UUID staffHospitalId;

    private String staffStatus;
    private LocalDateTime staffCreatedAt;
    private LocalDateTime staffUpdatedAt;
}
