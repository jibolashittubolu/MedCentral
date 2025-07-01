package com.example.v_medcentral.feature.appointment.mapper;

import com.example.v_medcentral.model.entity.Appointment;
import com.example.v_medcentral.model.request.CreateAppointmentRequest;
import com.example.v_medcentral.model.request.UpdateAppointmentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentRequestMapper {

    Appointment toEntity(CreateAppointmentRequest request);
    Appointment toEntity(UpdateAppointmentRequest request);
}
