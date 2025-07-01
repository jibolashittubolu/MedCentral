package com.example.medcentral.feature.staff.mapper;

import com.example.medcentral.feature.staff.model.response.StaffResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

@Component
public class StaffResponseMapper implements RowMapper<StaffResponse> {


    @Override
    public StaffResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        return StaffResponse.builder()
                .staffFirstName(rs.getString("staffFirstName"))
                .staffLastName(rs.getString("staffLastName"))
                .staffRole(rs.getString("staffRole"))
                .hospitalName(rs.getString("hospitalName"))
                .staffStatus(rs.getString("staffStatus"))

                .build();
    }
}
