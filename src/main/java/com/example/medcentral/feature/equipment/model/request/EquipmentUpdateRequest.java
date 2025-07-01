package com.example.medcentral.feature.equipment.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class EquipmentUpdateRequest {
    @JsonIgnore
    private String equipmentId;

    private String equipmentHospitalId;
    private String equipmentName;
    private String equipmentDescription;
    private String equipmentType;
    private Timestamp equipmentPurchaseDate;
    private Timestamp equipmentExpiryDate;
    private Timestamp equipmentLastMaintenanceDate;
    private Timestamp equipmentNextMaintenanceDate;

}

