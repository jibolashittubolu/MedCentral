package com.example.medcentral.feature.prescription.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class PrescriptionUpdateRequest {

    @JsonIgnore
    private String prescriptionId;

    private String prescriptionCheckInId;
    private String prescriptionMedicineId;
    private String prescriptionPrescribedById;
    private String prescriptionDosage;


//    @Min(value = 1, message = "Prescription quantity cannot be less than 1")
    private Long prescriptionQuantity;
    private String prescriptionStatus;
    private String prescriptionNotes;

//    private Timestamp prescriptionCreatedAt;
//    private Timestamp prescriptionUpdatedAt;
}

