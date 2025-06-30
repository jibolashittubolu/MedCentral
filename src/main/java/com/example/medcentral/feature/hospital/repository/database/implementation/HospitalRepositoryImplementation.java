package com.example.medcentral.feature.hospital.repository.database.implementation;

import com.example.medcentral.exception.ResourceNotFoundException;

import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.model.request.HospitalQueryParams;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import com.example.medcentral.feature.hospital.repository.database.query.HospitalQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HospitalRepositoryImplementation implements IHospitalRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public HospitalRepositoryImplementation(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createHospital(Hospital hospital) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("hospitalName", hospital.getHospitalName())
                .addValue("hospitalAddress", hospital.getHospitalAddress())
                .addValue("hospitalCity", hospital.getHospitalCity())
                .addValue("hospitalState", hospital.getHospitalState())
                .addValue("hospitalPhone", hospital.getHospitalPhone())
                .addValue("hospitalEmail", hospital.getHospitalEmail());

        return jdbcTemplate.update(
                HospitalQuery.CREATE_HOSPITAL,
                parameters
        );
    }

    @Override
    public List<Hospital> getHospitals(HospitalQueryParams params) {
        StringBuilder queryBuilder = new StringBuilder(HospitalQuery.GET_HOSPITALS);
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();

        BeanPropertyRowMapper<Hospital> rowMapper = new BeanPropertyRowMapper<>(Hospital.class);



        if (params.getHospitalId() != null) {
            queryBuilder.append(" AND hospitalId = :hospitalId");
            sqlParams.addValue("hospitalId", params.getHospitalId());
        }
        if (params.getHospitalName() != null) {
            queryBuilder.append(" AND hospitalName = :hospitalName");
            sqlParams.addValue("hospitalName", params.getHospitalName());
        }
        if (params.getHospitalAddress() != null) {
            queryBuilder.append(" AND hospitalAddress = :hospitalAddress");
            sqlParams.addValue("hospitalAddress", params.getHospitalAddress());
        }
        if (params.getHospitalCity() != null) {
            queryBuilder.append(" AND hospitalCity = :hospitalCity");
            sqlParams.addValue("hospitalCity", params.getHospitalCity());
        }
        if (params.getHospitalState() != null) {
            queryBuilder.append(" AND hospitalState = :hospitalState");
            sqlParams.addValue("hospitalState", params.getHospitalState());
        }
        if (params.getHospitalPhone() != null) {
            queryBuilder.append(" AND hospitalPhone = :hospitalPhone");
            sqlParams.addValue("hospitalPhone", params.getHospitalPhone());
        }
        if (params.getHospitalEmail() != null) {
            queryBuilder.append(" AND hospitalEmail = :hospitalEmail");
            sqlParams.addValue("hospitalEmail", params.getHospitalEmail());
        }
        if (params.getHospitalCreatedAt() != null) {
            queryBuilder.append(" AND hospitalCreatedAt = :hospitalCreatedAt");
            sqlParams.addValue("hospitalCreatedAt", params.getHospitalCreatedAt());
        }

        if (params.getHospitalUpdatedAt() != null) {
            queryBuilder.append(" AND hospitalUpdatedAt = :hospitalUpdatedAt");
            sqlParams.addValue("hospitalUpdatedAt", params.getHospitalUpdatedAt());
        }

        return jdbcTemplate.query( queryBuilder.toString(), sqlParams ,rowMapper);

    }


    public Hospital getHospitalById (String hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource("hospitalId", hospitalId);

        try {
            return jdbcTemplate.queryForObject(
                    HospitalQuery.GET_HOSPITAL_BY_ID,
                    params,
                    new BeanPropertyRowMapper<>(Hospital.class)
            );
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No hospital found with id " + hospitalId);
//            return null;  // or throw a custom ResourceNotFoundException if preferred
        }
    }


    @Override
    public long updateHospitalById(Hospital hospital) {

        return jdbcTemplate.update(HospitalQuery.UPDATE_HOSPITAL, new BeanPropertySqlParameterSource(hospital));
    }


    @Override
    public long deleteHospitalById(String hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.update(HospitalQuery.DELETE_HOSPITAL, params);
    }


}