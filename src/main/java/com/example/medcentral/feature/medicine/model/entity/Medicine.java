package com.example.medcentral.feature.medicine.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Medicine {

    private UUID medicineId;
    private UUID medicineHospitalId;

    private String medicineName;
    private String medicineDescription;

    private String medicineDosageForm;   // e.g., Tablet, Syrup
    private String medicineDosage;       // e.g., 500mg

    private LocalDate medicineExpiryDate;

    private Integer medicineStockQty;
    private BigDecimal medicineUnitPrice;

    private Integer medicineReorderLevel;

    private LocalDateTime medicineCreatedAt;
    private LocalDateTime medicineUpdatedAt;
}