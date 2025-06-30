package com.example.medcentral.feature.medicine.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAPIResponse<T> {

    private String medicineAPIResponseCode;
    private String medicineAPIResponseMessage;
    private T medicineAPIResponseData;
}
