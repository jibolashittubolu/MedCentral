package com.example.medcentral.feature.checkinstaff.mapper;

import com.example.medcentral.feature.checkinstaff.model.response.CheckInStaffResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CheckInStaffResponseMapper implements RowMapper<CheckInStaffResponse> {
    @Override
    public CheckInStaffResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CheckInStaffResponse.builder()
                .staffId(UUID.fromString(rs.getString("staffId")))
                .staffFirstName(rs.getString("staffFirstName"))
                .staffLastName(rs.getString("staffLastName"))
                .staffRole(rs.getString("staffRole"))
                .build();
    }
}
