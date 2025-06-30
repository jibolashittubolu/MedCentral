package com.example.medcentral.feature.sale.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Sale {

    private UUID saleId;
    private UUID saleCheckInId;
    private UUID saleHandledById; // Staff who processed the sale

    private BigDecimal saleTotal;

    private String saleStatus; // ACTIVE, VOIDED, REFUNDED

    private LocalDateTime saleCreatedAt;
    private LocalDateTime saleUpdatedAt;
}
