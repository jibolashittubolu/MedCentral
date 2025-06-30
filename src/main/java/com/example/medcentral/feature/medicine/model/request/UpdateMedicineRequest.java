package com.example.medcentral.feature.medicine.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class UpdateMedicineRequest {

    @NotNull(message = "Medicine ID is required for update")
    private UUID medicineId;

    private UUID medicineHospitalId;

    private String medicineName;

    private String medicineDescription;

    private String medicineDosageForm;

    private String medicineDosage;

    @Future(message = "Expiry date must be in the future")
    private LocalDate medicineExpiryDate;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer medicineStockQty;

    @DecimalMin(value = "0.00", inclusive = false, message = "Unit price must be greater than 0")
    private BigDecimal medicineUnitPrice;

    @Min(value = 0, message = "Reorder level cannot be negative")
    private Integer medicineReorderLevel;
}
