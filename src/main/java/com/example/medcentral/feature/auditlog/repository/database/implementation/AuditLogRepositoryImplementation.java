package com.example.medcentral.feature.auditlog.repository.database.implementation;

import com.example.medcentral.exception.ResourceNotFoundException;
import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.request.AuditLogQueryParams;
import com.example.medcentral.feature.auditlog.repository.database.interfaces.IAuditLogRepository;
import com.example.medcentral.feature.auditlog.repository.database.query.AuditLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditLogRepositoryImplementation implements IAuditLogRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AuditLogRepositoryImplementation(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createAuditLog(AuditLog auditLog) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("httpMethod", auditLog.getHttpMethod())
                .addValue("description", auditLog.getDescription())
                .addValue("tableName", auditLog.getTableName())
                .addValue("recordId", auditLog.getRecordId())
                .addValue("createdBy", auditLog.getCreatedBy())
                .addValue("oldValue", auditLog.getOldValue())
                .addValue("newValue", auditLog.getNewValue())
                .addValue("source", auditLog.getSource())
                .addValue("query", auditLog.getQuery())
                .addValue("ipAddress", auditLog.getIpAddress())
                .addValue( "userAgent" ,auditLog.getUserAgent());

//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        BeanPropertyRowMapper<AuditLog> rowMapper = new BeanPropertyRowMapper<>(AuditLog.class);

        return jdbcTemplate.update(
                AuditLogQuery.CREATE_AUDIT_LOG,
                parameters
        );
    }

    @Override
    public List<AuditLog> getAuditLogs(AuditLogQueryParams params) {
        StringBuilder queryBuilder = new StringBuilder(AuditLogQuery.GET_AUDIT_LOGS);
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();

        BeanPropertyRowMapper<AuditLog> rowMapper = new BeanPropertyRowMapper<>(AuditLog.class);



        if (params.getAuditLogId() != null) {
            queryBuilder.append(" AND auditLogId = :auditLogId");
            sqlParams.addValue("auditLogId", params.getAuditLogId());
        }
        if (params.getHttpMethod() != null) {
            queryBuilder.append(" AND httpMethod = :httpMethod");
            sqlParams.addValue("httpMethod", params.getHttpMethod());
        }
        if (params.getDescription() != null) {
            queryBuilder.append(" AND description = :description");
            sqlParams.addValue("description", params.getDescription());
        }
        if (params.getTableName() != null) {
            queryBuilder.append(" AND tableName = :tableName");
            sqlParams.addValue("tableName", params.getTableName());
        }
        if (params.getRecordId() != null) {
            queryBuilder.append(" AND recordId = :recordId");
            sqlParams.addValue("recordId", params.getRecordId());
        }
        if (params.getCreatedBy() != null) {
            queryBuilder.append(" AND createdBy = :createdBy");
            sqlParams.addValue("createdBy", params.getCreatedBy());
        }
        if (params.getOldValue() != null) {
            queryBuilder.append(" AND oldValue = :oldValue");
            sqlParams.addValue("oldValue", params.getOldValue());
        }
        if (params.getNewValue() != null) {
            queryBuilder.append(" AND newValue = :newValue");
            sqlParams.addValue("newValue", params.getNewValue());
        }
        if (params.getSource() != null) {
            queryBuilder.append(" AND source = :source");
            sqlParams.addValue("source", params.getSource());
        }
        if (params.getQuery() != null) {
            queryBuilder.append(" AND query = :query");
            sqlParams.addValue("query", params.getQuery());
        }
        if (params.getIpAddress() != null) {
            queryBuilder.append(" AND ipAddress = :ipAddress");
            sqlParams.addValue("ipAddress", params.getIpAddress());
        }
        if (params.getUserAgent() != null) {
            queryBuilder.append(" AND userAgent = :userAgent");
            sqlParams.addValue("userAgent", params.getUserAgent());
        }
        if (params.getStatus() != null) {
            queryBuilder.append(" AND status = :status");
            sqlParams.addValue("status", params.getStatus());
        }



        return jdbcTemplate.query( queryBuilder.toString(), sqlParams ,rowMapper);

    }


    public AuditLog getAuditLogById (String auditLogId) {
        MapSqlParameterSource params = new MapSqlParameterSource("auditLogId", auditLogId);

        try {
            return jdbcTemplate.queryForObject(
                    AuditLogQuery.GET_AUDIT_LOG_BY_ID,
                    params,
                    new BeanPropertyRowMapper<>(AuditLog.class)
            );
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No auditLog found with id " + auditLogId);
//            return null;  // or throw a custom ResourceNotFoundException if preferred
        }
    }


}