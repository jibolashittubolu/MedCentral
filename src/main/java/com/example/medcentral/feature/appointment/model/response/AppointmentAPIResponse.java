package com.example.v_medcentral.feature.appointment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentAPIResponse <T> {

    private String appointmentAPIResponseCode;
    private String appointmentAPIResponseMessage;
    private T appointmentAPIResponseData;
}
