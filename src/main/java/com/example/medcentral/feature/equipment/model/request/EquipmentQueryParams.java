package com.example.medcentral.feature.equipment.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EquipmentQueryParams {

    private String equipmentId;
    private String equipmentHospitalId;
    private String equipmentName;
    private String equipmentDescription;
    private String equipmentType;
    private Timestamp equipmentPurchaseDate;
    private Timestamp equipmentExpiryDate;
    private Timestamp equipmentLastMaintenanceDate;
    private Timestamp equipmentNextMaintenanceDate;


    private Timestamp equipmentCreatedAt;
    private Timestamp equipmentUpdatedAt;

//    @NotBlank(message = "specify the equipmentCondition")
//    private String equipmentCondition;
}
