package com.example.medcentral.feature.sale.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CreateSaleRequest {

    @NotNull(message = "Check-in ID is required")
    private UUID saleCheckInId;

    @NotNull(message = "Handled-by staff ID is required")
    private UUID saleHandledById;

    @NotNull(message = "Sale total is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Sale total must be greater than 0")
    private BigDecimal saleTotal;

//    @Pattern(regexp = "ACTIVE|VOIDED|REFUNDED", message = "Status must be ACTIVE, VOIDED, or REFUNDED")
    private String saleStatus; // Optional, defaults to ACTIVE if not provided
}
