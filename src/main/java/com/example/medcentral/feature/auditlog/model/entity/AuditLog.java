package com.example.medcentral.feature.auditlog.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor   // Generates a public no-argument constructor
@AllArgsConstructor  // Generates a public all-arguments constructor
public class AuditLog {

    private long auditLogId;
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
