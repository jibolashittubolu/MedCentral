package com.example.medcentral.feature.hospital.model.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class HospitalResponse {
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

