package com.example.medcentral.feature.patient.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientAPIResponse<T> {

    private String patientAPIResponseCode;
    private String patientAPIResponseMessage;
    private T patientAPIResponseData;
}
