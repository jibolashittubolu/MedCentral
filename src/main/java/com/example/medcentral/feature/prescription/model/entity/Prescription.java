package com.example.medcentral.feature.prescription.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor   // Generates a public no-argument constructor
@AllArgsConstructor  // Generates a public all-arguments constructor
public class Prescription {
    private String prescriptionId;
    private String prescriptionCheckInId;
    private String prescriptionMedicineId;
    private String prescriptionPrescribedById;
    private String prescriptionDosage;


    private long prescriptionQuantity;
    private String prescriptionStatus;
    private String prescriptionNotes;

    private Timestamp prescriptionCreatedAt;
    private Timestamp prescriptionUpdatedAt;
}
