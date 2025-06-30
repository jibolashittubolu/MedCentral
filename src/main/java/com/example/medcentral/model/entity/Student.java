package com.example.medcentral.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor   // Generates a public no-argument constructor
@AllArgsConstructor  // Generates a public all-arguments constructor
public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private int studentAge;
    private String studentMatricNumber;
    private String studentStateOfOrigin;

    private String studentStatus;

    private Timestamp studentCreatedAt;
    private Timestamp studentUpdatedAt;
}
