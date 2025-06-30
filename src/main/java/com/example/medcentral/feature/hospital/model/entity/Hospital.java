package com.example.medcentral.feature.hospital.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor   // Generates a public no-argument constructor
@AllArgsConstructor  // Generates a public all-arguments constructor
public class Hospital {

    private String hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalCity;
    private String hospitalState;

    private String hospitalPhone;
    private String hospitalEmail;
    private String hospitalStatus;

    private Timestamp hospitalCreatedAt;
    private Timestamp hospitalUpdatedAt;
}
