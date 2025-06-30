package com.example.medcentral.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private int studentAge;
    private String studentMatricNumber;
    private String studentStateOfOrigin;
    private String studentStatus;
    private String studentCreatedAt;
    private String studentUpdatedAt;
}

