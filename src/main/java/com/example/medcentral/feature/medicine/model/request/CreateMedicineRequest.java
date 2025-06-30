package com.example.medcentral.feature.medicine.model.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateMedicineRequest {

    @NotNull(message = "Hospital ID is required")
    private UUID medicineHospitalId;

    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    private String medicineDescription;

    private String medicineDosageForm;   // e.g., Tablet, Syrup
    private String medicineDosage;       // e.g., 500mg

    @Future(message = "Expiry date must be in the future")
    private LocalDate medicineExpiryDate;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer medicineStockQty;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Unit price must be greater than 0")
    private BigDecimal medicineUnitPrice;

    @Min(value = 0, message = "Reorder level cannot be negative")
    private Integer medicineReorderLevel;
}
