package com.example.v_medcentral.feature.appointment.repository.database.interfaces;

import com.example.v_medcentral.model.entity.Appointment;
import com.example.v_medcentral.model.response.AppointmentResponse;

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
