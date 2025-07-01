package com.example.medcentral.feature.prescription.model.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PrescriptionQueryParams {

    private String prescriptionId;
    private String prescriptionCheckInId;
    private String prescriptionMedicineId;
    private String prescriptionPrescribedById;
    private String prescriptionDosage;


    private Long prescriptionQuantity;
//    private String prescriptionStatus;
    private String prescriptionNotes;

    private Timestamp prescriptionCreatedAt;
    private Timestamp prescriptionUpdatedAt;
}
