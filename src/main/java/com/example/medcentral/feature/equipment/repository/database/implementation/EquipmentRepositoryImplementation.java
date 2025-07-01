package com.example.medcentral.feature.equipment.repository.database.implementation;

import com.example.medcentral.exception.ResourceNotFoundException;


import com.example.medcentral.feature.equipment.model.entity.Equipment;
import com.example.medcentral.feature.equipment.model.request.EquipmentQueryParams;
import com.example.medcentral.feature.equipment.repository.database.interfaces.IEquipmentRepository;
import com.example.medcentral.feature.equipment.repository.database.query.EquipmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepositoryImplementation implements IEquipmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EquipmentRepositoryImplementation(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createEquipment(Equipment equipment) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("equipmentName", equipment.getEquipmentNextMaintenanceDate())
                .addValue("equipmentDescription", equipment.getEquipmentDescription())
                .addValue("equipmentType", equipment.getEquipmentType())
                .addValue("equipmentPurchaseDate", equipment.getEquipmentPurchaseDate())
                .addValue("equipmentExpiryDate", equipment.getEquipmentExpiryDate())
                .addValue("equipmentLastMaintenanceDate", equipment.getEquipmentLastMaintenanceDate())
                .addValue("equipmentNextMaintenanceDate", equipment.getEquipmentNextMaintenanceDate());

        return jdbcTemplate.update(
                EquipmentQuery.CREATE_EQUIPMENT,
                parameters
        );
    }

    @Override
    public List<Equipment> getEquipments(EquipmentQueryParams params) {
        StringBuilder queryBuilder = new StringBuilder(EquipmentQuery.GET_EQUIPMENTS);
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();

        BeanPropertyRowMapper<Equipment> rowMapper = new BeanPropertyRowMapper<>(Equipment.class);



        if (params.getEquipmentHospitalId() != null) {
            queryBuilder.append(" AND equipmentHospitalId = :equipmentHospitalId");
            sqlParams.addValue("equipmentHospitalId", params.getEquipmentHospitalId());
        }
        if (params.getEquipmentName() != null) {
            queryBuilder.append(" AND equipmentName = :equipmentName");
            sqlParams.addValue("equipmentName", params.getEquipmentName());
        }
        if (params.getEquipmentDescription() != null) {
            queryBuilder.append(" AND equipmentDescription = :equipmentDescription");
            sqlParams.addValue("equipmentDescription", params.getEquipmentDescription());
        }
        if (params.getEquipmentType() != null) {
            queryBuilder.append(" AND equipmentType = :equipmentType");
            sqlParams.addValue("equipmentType", params.getEquipmentType());
        }
        if (params.getEquipmentPurchaseDate() != null) {
            queryBuilder.append(" AND equipmentPurchaseDate = :equipmentPurchaseDate");
            sqlParams.addValue("equipmentPurchaseDate", params.getEquipmentPurchaseDate());
        }
        if (params.getEquipmentExpiryDate() != null) {
            queryBuilder.append(" AND equipmentExpiryDate = :equipmentExpiryDate");
            sqlParams.addValue("equipmentExpiryDate", params.getEquipmentExpiryDate());
        }
        if (params.getEquipmentLastMaintenanceDate() != null) {
            queryBuilder.append(" AND equipmentLastMaintenanceDate = :equipmentLastMaintenanceDate");
            sqlParams.addValue("equipmentLastMaintenanceDate", params.getEquipmentLastMaintenanceDate());
        }
        if (params.getEquipmentNextMaintenanceDate() != null) {
            queryBuilder.append(" AND equipmentNextMaintenanceDate = :equipmentNextMaintenanceDate");
            sqlParams.addValue("equipmentNextMaintenanceDate", params.getEquipmentNextMaintenanceDate());
        }
        if (params.getEquipmentCreatedAt() != null) {
            queryBuilder.append(" AND equipmentCreatedAt = :equipmentCreatedAt");
            sqlParams.addValue("equipmentCreatedAt", params.getEquipmentCreatedAt());
        }

        if (params.getEquipmentUpdatedAt() != null) {
            queryBuilder.append(" AND equipmentUpdatedAt = :equipmentUpdatedAt");
            sqlParams.addValue("equipmentUpdatedAt", params.getEquipmentUpdatedAt());
        }

        return jdbcTemplate.query( queryBuilder.toString(), sqlParams ,rowMapper);
    }


    public Equipment getEquipmentById (String equipmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource("equipmentId", equipmentId);

        try {
            return jdbcTemplate.queryForObject(
                    EquipmentQuery.GET_EQUIPMENT_BY_ID,
                    params,
                    new BeanPropertyRowMapper<>(Equipment.class)
            );
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No equipment found with id " + equipmentId);
//            return null;  // or throw a custom ResourceNotFoundException if preferred
        }
    }


    @Override
    public long updateEquipmentById(Equipment equipment) {

        return jdbcTemplate.update(EquipmentQuery.UPDATE_EQUIPMENT, new BeanPropertySqlParameterSource(equipment));
    }


    @Override
    public long deleteEquipmentById(String equipmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource("equipmentId", equipmentId);
        return jdbcTemplate.update(EquipmentQuery.DELETE_EQUIPMENT, params);
    }


}