package com.example.medcentral.feature.patient.util.validator;

import com.example.medcentral.feature.patient.model.response.PatientResponse;

public class PatientValidator {
    public static void validate(PatientResponse patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found.");
        }
    }
}