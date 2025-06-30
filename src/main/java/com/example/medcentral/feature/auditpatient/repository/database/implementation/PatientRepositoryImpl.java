package com.example.medcentral.feature.auditpatient.repository.database.implementation;

import com.example.medcentral.feature.auditpatient.mapper.PatientResponseMapper;
import com.example.medcentral.feature.auditpatient.model.entity.Patient;
import com.example.medcentral.feature.auditpatient.model.response.PatientResponse;
import com.example.medcentral.feature.auditpatient.repository.database.interfaces.PatientRepository;
import com.example.medcentral.feature.auditpatient.repository.database.query.PatientQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PatientRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createPatient(Patient patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientFirstName", patient.getPatientFirstName())
                .addValue("patientLastName", patient.getPatientLastName())
                .addValue("patientOtherNames", patient.getPatientOtherNames())
                .addValue("patientDateOfBirth", patient.getPatientDateOfBirth())
                .addValue("patientGender", patient.getPatientGender())
                .addValue("patientPhone", patient.getPatientPhone())
                .addValue("patientEmail", patient.getPatientEmail())
                .addValue("patientAddress", patient.getPatientAddress())
                .addValue("patientCountry", patient.getPatientCountry())
                .addValue("patientEmergencyContactName", patient.getPatientEmergencyContactName())
                .addValue("patientEmergencyContactPhone", patient.getPatientEmergencyContactPhone())
                .addValue("patientBloodGroup", patient.getPatientBloodGroup())
                .addValue("patientGenotype", patient.getPatientGenotype())
                .addValue("patientRegisteredAt", patient.getPatientRegisteredAt())
                .addValue("patientAccessScope", patient.getPatientAccessScope());

        return jdbcTemplate.update(PatientQuery.CREATE_PATIENT, params);
    }

    @Override
    public int updatePatient(Patient patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", patient.getPatientId())
                .addValue("patientFirstName", patient.getPatientFirstName())
                .addValue("patientLastName", patient.getPatientLastName())
                .addValue("patientOtherNames", patient.getPatientOtherNames())
                .addValue("patientDateOfBirth", patient.getPatientDateOfBirth())
                .addValue("patientGender", patient.getPatientGender())
                .addValue("patientPhone", patient.getPatientPhone())
                .addValue("patientEmail", patient.getPatientEmail())
                .addValue("patientAddress", patient.getPatientAddress())
                .addValue("patientCountry", patient.getPatientCountry())
                .addValue("patientEmergencyContactName", patient.getPatientEmergencyContactName())
                .addValue("patientEmergencyContactPhone", patient.getPatientEmergencyContactPhone())
                .addValue("patientBloodGroup", patient.getPatientBloodGroup())
                .addValue("patientGenotype", patient.getPatientGenotype())
                .addValue("patientRegisteredAt", patient.getPatientRegisteredAt())
                .addValue("patientAccessScope", patient.getPatientAccessScope());

        return jdbcTemplate.update(PatientQuery.UPDATE_PATIENT, params);
    }

    @Override
    public PatientResponse getPatientById(UUID patientId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", patientId);

        List<PatientResponse> results =  jdbcTemplate.query(PatientQuery.GET_PATIENT_BY_ID, params,
                new PatientResponseMapper());

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<PatientResponse> getAllPatients() {

        return jdbcTemplate.query(PatientQuery.GET_ALL_PATIENT, new PatientResponseMapper());
    }

    @Override
    public List<PatientResponse> getPatientsBySearchQuery(String searchQuery) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("searchQuery", "%"+searchQuery+"%");

        return jdbcTemplate.query(PatientQuery.GET_PATIENT_BY_SEARCH_QUERY, params,
                new PatientResponseMapper());
    }

    @Override
    public int deletePatientById(UUID patientId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", patientId);

        return jdbcTemplate.update(PatientQuery.DELETE_PATIENT_BY_ID, params);
    }

//    @Override
//    public List<CheckIn> getCheckInsByPatientId(UUID patientId) {
//        return List.of();
//    }
}
