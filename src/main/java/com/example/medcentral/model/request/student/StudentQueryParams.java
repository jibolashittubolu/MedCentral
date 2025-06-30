package com.example.medcentral.model.request.student;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StudentQueryParams {

    private String stateOfOrigin;

    private String studentFirstName;

    private String studentLastName;

    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;
}
