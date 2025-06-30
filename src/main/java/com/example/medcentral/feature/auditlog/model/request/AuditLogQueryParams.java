package com.example.medcentral.feature.auditlog.model.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AuditLogQueryParams {

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
    private String status;
}
