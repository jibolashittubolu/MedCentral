package com.example.medcentral.feature.appointment.util.validator;

import com.example.medcentral.feature.appointment.model.response.AppointmentResponse;

public class AppointmentValidator {
    public static void validate(AppointmentResponse appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found.");
        }
        String status = appointment.getAppointmentStatus();
        if ("DELETED".equalsIgnoreCase(status) || "CANCELLED".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Appointment has been DELETED or CANCELLED.");
        }
        if ("COMPLETED".equalsIgnoreCase(status)) {
            throw new IllegalArgumentException("Appointment has already been COMPLETED.");
        }
    }
}
