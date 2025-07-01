package com.example.medcentral.feature.appointment.mapper;

import com.example.medcentral.feature.appointment.model.response.AppointmentResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class AppointmentResponseMapper implements RowMapper<AppointmentResponse> {
    @Override
    public AppointmentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        return AppointmentResponse.builder()
                .patientFirstName(rs.getString("patientFirstName"))
                .patientLastName(rs.getString("patientLastName"))
                .hospitalName(rs.getString("hospitalName"))
                .staffAssigned(rs.getString("staffAssigned"))
                .appointmentId(UUID.fromString(rs.getString("appointmentId")))
                .appointmentReason(rs.getString("appointmentReason"))
                .appointmentStatus(rs.getString("appointmentStatus"))
                .build();
    }
}
