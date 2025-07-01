package com.example.medcentral.feature.appointment.repository.database.interfaces;

import com.example.medcentral.feature.appointment.model.entity.Appointment;
import com.example.medcentral.feature.appointment.model.response.AppointmentResponse;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository {

    int createAppointment(Appointment appointment);
    int updateAppointment(Appointment appointment);
    int rescheduleAppointment(Appointment appointment);
    int cancelAppointment(UUID appointmentId);
    int deleteAppointment(UUID appointmentId);
    AppointmentResponse  getAppointmentById(UUID appointmentId);
    List<AppointmentResponse> getAppointmentByPatientId(UUID appointmentPatientId);
}
