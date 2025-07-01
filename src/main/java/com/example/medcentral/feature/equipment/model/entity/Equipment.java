package com.example.medcentral.feature.equipment.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor   // Generates a public no-argument constructor
@AllArgsConstructor  // Generates a public all-arguments constructor
public class Equipment {
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
