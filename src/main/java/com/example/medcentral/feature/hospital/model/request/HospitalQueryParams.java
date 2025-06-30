package com.example.medcentral.feature.hospital.model.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HospitalQueryParams {

    private String hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalCity;
    private String hospitalState;

    private String hospitalPhone;
    private String hospitalEmail;

    private Timestamp hospitalCreatedAt;
    private Timestamp hospitalUpdatedAt;
}
