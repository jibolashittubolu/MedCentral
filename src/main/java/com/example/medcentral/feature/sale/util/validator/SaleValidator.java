package com.example.medcentral.feature.sale.util.validator;

import com.example.medcentral.feature.sale.model.response.SaleResponse;

public class SaleValidator {
    public static void validate(SaleResponse sale) {
        if (sale == null) {
            throw new IllegalArgumentException("Sale not found.");
        }
    }
}
