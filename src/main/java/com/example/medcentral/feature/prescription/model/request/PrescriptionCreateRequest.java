package com.example.medcentral.feature.prescription.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class PrescriptionCreateRequest {

    @NotBlank(message = "specify the prescriptionCheckInId")
    private String prescriptionCheckInId;

    @NotBlank(message = "specify the prescriptionMedicineId")
    private String prescriptionMedicineId;

    @NotBlank(message = "specify the prescriptionPrescribedById")
    private String prescriptionPrescribedById;

    @NotBlank(message = "specify the prescriptionDosage")
    private String prescriptionDosage;

//    @NotBlank(message = "specify the prescriptionQuantity")
    @Min(value = 1, message = "Prescription quantity cannot be less than 1")
    private Long prescriptionQuantity;


    @NotBlank(message = "specify the prescriptionNotes")
    private String prescriptionNotes;

}
