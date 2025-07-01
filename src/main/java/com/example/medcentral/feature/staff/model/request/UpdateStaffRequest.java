package com.example.medcentral.feature.staff.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class UpdateStaffRequest {

    @NotNull(message = "Staff Id is required")
    private UUID staffId;

    private String staffFirstName;
    private String staffLastName;
    private String staffOtherNames;

    @Past(message = "Date of birth must be in the past")
    private LocalDateTime staffDateOfBirth;
    private String staffGender;

    private String staffPhone;
    @Email(message = "Invalid email format")
    private String staffEmail;
    private String staffAddress;

    private String staffRole;
    private String staffDepartment;

    private UUID staffHospitalId;

    private String staffStatus;
}
