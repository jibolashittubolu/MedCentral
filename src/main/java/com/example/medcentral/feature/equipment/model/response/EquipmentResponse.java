package com.example.medcentral.feature.equipment.model.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EquipmentResponse {
    private String equipmentId;
    private String equipmentHospitalId;
    private String equipmentName;
    private String equipmentDescription;
    private String equipmentType;

    private Timestamp equipmentPurchaseDate;
    private Timestamp equipmentExpiryDate;

    private Timestamp equipmentLastMaintenanceDate;
    private Timestamp equipmentNextMaintenanceDate;
    private String equipmentCondition;

    private Timestamp equipmentCreatedAt;
    private Timestamp equipmentUpdatedAt;
}

