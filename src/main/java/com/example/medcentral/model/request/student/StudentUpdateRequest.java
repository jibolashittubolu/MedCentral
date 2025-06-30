package com.example.medcentral.model.request.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentUpdateRequest {
    @NotNull(message = "Student ID is required")
    @Min(value = 100, message = "studentId must be ≥ 1")
    private Long studentId;

    private String studentFirstName;
    private String studentLastName;
    private Integer studentAge;
    private String studentMatricNumber;
    private String studentStateOfOrigin;
}
