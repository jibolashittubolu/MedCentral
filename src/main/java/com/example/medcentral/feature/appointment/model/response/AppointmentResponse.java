package com.example.v_medcentral.feature.appointment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {

    private String patientFirstName;
    private String patientLastName;

    private String hospitalName;

    private String staffAssigned;

    private UUID appointmentId;
    private String appointmentReason;
    private String appointmentStatus;
}
