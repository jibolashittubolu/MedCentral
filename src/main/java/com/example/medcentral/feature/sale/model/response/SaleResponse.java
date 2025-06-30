package com.example.medcentral.feature.sale.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {

    private UUID checkInId;
    private String handledBy;
    private BigDecimal saleTotal;
    private String saleStatus;
    private String medicineSold;
}
