package com.example.medcentral.feature.medicine.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponse {

    private String hospitalName;

    private UUID medicineId;
    private String medicineName;
    private String medicineDescription;
    private String medicineDosageForm;
    private String medicineDosage;
    private String medicineExpiryDate;
    private String medicineStockQty;
    private String medicineUnitPrice;
    private String medicineReorderLevel;
}
