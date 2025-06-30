package com.example.medcentral.feature.checkin.mapper;

import com.example.medcentral.feature.checkin.model.response.CheckInResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CheckInResponseMapper implements RowMapper<CheckInResponse> {

    @Override
    public CheckInResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        return CheckInResponse.builder()
                .patientFirstName(rs.getString("patientFirstName"))
                .patientLastName(rs.getString("patientLastName"))
                .hospitalName(rs.getString("hospitalName"))
                .checkInId(UUID.fromString(rs.getString("checkInId")))
                .checkInReason(rs.getString("checkInReason"))
                .checkInDiagnosis(rs.getString("checkInDiagnosis"))
                .checkInStatus(rs.getString("checkInStatus"))
                .checkInPatientNotes(rs.getString("checkInPatientNotes"))
                .staffAssigned(rs.getString("staffAssigned"))
                .build();
    }
}
