package com.example.medcentral.feature.equipment.model.request;//package com.example.medcentral.feature.hospital.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class EquipmentCreateRequest {


//    @NotBlank(message = "specify the equipmentId")
//    private String equipmentId;

    @NotBlank(message = "specify the equipmentHospitalId")
    private String equipmentHospitalId;

    @NotBlank(message = "specify the equipmentName")
    private String equipmentName;

    @NotBlank(message = "specify the equipmentDescription")
    private String equipmentDescription;

    @NotBlank(message = "specify the equipmentType")
    private String equipmentType;

    @NotBlank(message = "specify the equipmentPurchaseDate")
    private Timestamp equipmentPurchaseDate;

    @NotBlank(message = "specify the equipmentExpiryDate")
    private Timestamp equipmentExpiryDate;

    @NotBlank(message = "specify the equipmentLastMaintenanceDate")
    private Timestamp equipmentLastMaintenanceDate;

    @NotBlank(message = "specify the equipmentNexMaintenanceDate")
    private Timestamp equipmentNextMaintenanceDate;

//    @NotBlank(message = "specify the equipmentCondition")
//    private String equipmentCondition;

}
