package com.example.medcentral.feature.patient.mapper;

import com.example.medcentral.feature.patient.model.response.PatientResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class PatientResponseMapper implements RowMapper<PatientResponse> {

    @Override
    public PatientResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        return PatientResponse.builder()
                .patientFirstName(rs.getString("patientFirstName"))
                .patientLastName(rs.getString("patientLastName"))
                .patientOtherNames(rs.getString("patientOtherNames"))
                .patientDateOfBirth(LocalDateTime.parse(rs.getString("patientDateOfBirth"), formatter))
                .patientGender(rs.getString("patientGender"))
                .patientPhone(rs.getString("patientPhone"))
                .patientEmail(rs.getString("patientEmail"))
                .patientAddress(rs.getString("patientAddress"))
                .patientCountry(rs.getString("patientCountry"))
                .patientEmergencyContactName(rs.getString("patientEmergencyContactName"))
                .patientEmergencyContactPhone(rs.getString("patientEmergencyContactPhone"))
                .patientBloodGroup(rs.getString("patientBloodGroup"))
                .patientGenotype(rs.getString("patientGenotype"))
                .patientRegisteredAt(UUID.fromString(rs.getString("patientRegisteredAt")))
                .patientAccessScope(rs.getString("patientAccessScope"))

                .build();
    }

}
