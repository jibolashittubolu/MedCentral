package com.example.medcentral.feature.prescription.model.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PrescriptionResponse {
    private String prescriptionId;
    private String prescriptionCheckInId;
    private String prescriptionMedicineId;
    private String prescriptionPrescribedById;
    private String prescriptionDosage;


    private long prescriptionQuantity;
//    private String prescriptionStatus;
    private String prescriptionNotes;

    private Timestamp prescriptionCreatedAt;
    private Timestamp prescriptionUpdatedAt;
}

