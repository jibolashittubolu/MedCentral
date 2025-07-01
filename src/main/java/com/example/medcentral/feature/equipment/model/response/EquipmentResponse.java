package com.example.medcentral.feature.equipment.model.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EquipmentResponse {
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

