package com.example.v_medcentral.feature.staff.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class CreateStaffRequest {

    @NotBlank(message = "Staff first name is required")
    private String staffFirstName;

    @NotBlank(message = "Staff last name is required")
    private String staffLastName;
    private String staffOtherNames;

    @Past(message = "Date of birth must be in the past")
    @NotNull(message = "Date of birth is required")
    private LocalDateTime staffDateOfBirth;
    private String staffGender;

    private String staffPhone;
    @Email(message = "Invalid email format")
    private String staffEmail;
    private String staffAddress;

    private String staffRole;
    private String staffDepartment;

    @NotNull(message = "Staff hospital id is required")
    private UUID staffHospitalId;
}
