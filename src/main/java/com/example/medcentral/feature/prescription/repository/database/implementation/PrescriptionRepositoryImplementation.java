package com.example.medcentral.feature.prescription.repository.database.implementation;

import com.example.medcentral.exception.ResourceNotFoundException;


import com.example.medcentral.feature.prescription.model.entity.Prescription;
import com.example.medcentral.feature.prescription.model.request.PrescriptionQueryParams;
import com.example.medcentral.feature.prescription.repository.database.interfaces.IPrescriptionRepository;
import com.example.medcentral.feature.prescription.repository.database.query.PrescriptionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrescriptionRepositoryImplementation implements IPrescriptionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PrescriptionRepositoryImplementation(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createPrescription(Prescription prescription) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("prescriptionCheckInId", prescription.getPrescriptionCheckInId())
                .addValue("prescriptionMedicineId", prescription.getPrescriptionMedicineId())
                .addValue("prescriptionPrescribedById", prescription.getPrescriptionPrescribedById())
                .addValue("prescriptionDosage", prescription.getPrescriptionDosage())
                .addValue("prescriptionQuantity", prescription.getPrescriptionQuantity())
                .addValue("prescriptionNotes", prescription.getPrescriptionNotes());

        return jdbcTemplate.update(
                PrescriptionQuery.CREATE_PRESCRIPTION,
                parameters
        );
    }

    @Override
    public List<Prescription> getPrescriptions(PrescriptionQueryParams params) {
        StringBuilder queryBuilder = new StringBuilder(PrescriptionQuery.GET_PRESCRIPTIONS);
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();

        if (params.getPrescriptionId() != null) {
            queryBuilder.append(" AND prescriptionId = :prescriptionId");
            sqlParams.addValue("prescriptionId", params.getPrescriptionId());
        }
        if (params.getPrescriptionCheckInId() != null) {
            queryBuilder.append(" AND prescriptionCheckInId = :prescriptionCheckInId");
            sqlParams.addValue("prescriptionCheckInId", params.getPrescriptionCheckInId());
        }
        if (params.getPrescriptionMedicineId() != null) {
            queryBuilder.append(" AND prescriptionMedicineId = :prescriptionMedicineId");
            sqlParams.addValue("prescriptionMedicineId", params.getPrescriptionMedicineId());
        }
        if (params.getPrescriptionPrescribedById() != null) {
            queryBuilder.append(" AND prescriptionPrescribedById = :prescriptionPrescribedById");
            sqlParams.addValue("prescriptionPrescribedById", params.getPrescriptionPrescribedById());
        }
        if (params.getPrescriptionDosage() != null) {
            queryBuilder.append(" AND prescriptionDosage = :prescriptionDosage");
            sqlParams.addValue("prescriptionDosage", params.getPrescriptionDosage());
        }
        if (params.getPrescriptionQuantity() != null && params.getPrescriptionQuantity() > 0) {
            queryBuilder.append(" AND prescriptionQuantity = :prescriptionQuantity");
            sqlParams.addValue("prescriptionQuantity", params.getPrescriptionQuantity());
        }
        if (params.getPrescriptionNotes() != null) {
            queryBuilder.append(" AND prescriptionNotes LIKE :prescriptionNotes");
            sqlParams.addValue("prescriptionNotes", "%" + params.getPrescriptionNotes() + "%");
        }
        if (params.getPrescriptionCreatedAt() != null) {
            queryBuilder.append(" AND prescriptionCreatedAt = :prescriptionCreatedAt");
            sqlParams.addValue("prescriptionCreatedAt", params.getPrescriptionCreatedAt());
        }
        if (params.getPrescriptionUpdatedAt() != null) {
            queryBuilder.append(" AND prescriptionUpdatedAt = :prescriptionUpdatedAt");
            sqlParams.addValue("prescriptionUpdatedAt", params.getPrescriptionUpdatedAt());
        }

        BeanPropertyRowMapper<Prescription> rowMapper = new BeanPropertyRowMapper<>(Prescription.class);
        return jdbcTemplate.query(queryBuilder.toString(), sqlParams, rowMapper);
    }



    public Prescription getPrescriptionById (String prescriptionId) {
        MapSqlParameterSource params = new MapSqlParameterSource("prescriptionId", prescriptionId);

        try {
            return jdbcTemplate.queryForObject(
                    PrescriptionQuery.GET_PRESCRIPTION_BY_ID,
                    params,
                    new BeanPropertyRowMapper<>(Prescription.class)
            );
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No prescription found with id " + prescriptionId);
//            return null;  // or throw a custom ResourceNotFoundException if preferred
        }
    }


    @Override
    public long updatePrescriptionById(Prescription prescription) {

        return jdbcTemplate.update(PrescriptionQuery.UPDATE_PRESCRIPTION, new BeanPropertySqlParameterSource(prescription));
    }


    @Override
    public long deletePrescriptionById(String prescriptionId) {
        MapSqlParameterSource params = new MapSqlParameterSource("prescriptionId", prescriptionId);
        return jdbcTemplate.update(PrescriptionQuery.DELETE_PRESCRIPTION, params);
    }


}