package com.example.medcentral.feature.auditlog.model.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AuditLogResponse {
    private String auditLogId;
    private String httpMethod;
    private String description;
    private String tableName;
    private String recordId;
    private String createdBy;

    private String oldValue;
    private String newValue;
    private String source;
    private String query;
    private String ipAddress;
    private String userAgent;

    private Timestamp createdAt;
}

