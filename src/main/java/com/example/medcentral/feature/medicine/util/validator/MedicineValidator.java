package com.example.medcentral.feature.medicine.util.validator;

import com.example.medcentral.feature.medicine.model.response.MedicineResponse;

public class MedicineValidator {
    public static void validate(MedicineResponse medicine) {
        if (medicine == null) {
            throw new IllegalArgumentException("Medicine not found.");
        }
    }
}
