package com.example.medcentral.feature.auditpatient.util.validator;

import com.example.medcentral.feature.auditpatient.model.response.PatientResponse;

public class PatientValidator {
    public static void validate(PatientResponse patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found.");
        }
    }
}