package com.example.medcentral.feature.appointment.mapper;

import com.example.medcentral.feature.appointment.model.entity.Appointment;
import com.example.medcentral.feature.appointment.model.request.CreateAppointmentRequest;
import com.example.medcentral.feature.appointment.model.request.UpdateAppointmentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentRequestMapper {

    Appointment toEntity(CreateAppointmentRequest request);
    Appointment toEntity(UpdateAppointmentRequest request);
}
