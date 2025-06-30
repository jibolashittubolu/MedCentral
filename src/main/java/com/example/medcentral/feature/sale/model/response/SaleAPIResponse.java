package com.example.medcentral.feature.sale.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleAPIResponse<T> {

    private String saleAPIResponseCode;
    private String saleAPIResponseMessage;
    private T saleAPIResponseData;
}
