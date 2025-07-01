package com.example.medcentral.feature.sale.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class UpdateSaleRequest {

    @NotNull(message = "Sale ID is required for update")
    private UUID saleId;

    private UUID saleCheckInId;

    private UUID saleHandledById;

    @DecimalMin(value = "0.00", inclusive = false, message = "Sale total must be greater than 0")
    private BigDecimal saleTotal;

//    @Pattern(regexp = "ACTIVE|VOIDED|REFUNDED", message = "Status must be ACTIVE, VOIDED, or REFUNDED")
    private String saleStatus;
}
