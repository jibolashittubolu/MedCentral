package com.example.medcentral.feature.auditlog.repository.database.interfaces;


import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.request.AuditLogQueryParams;

import java.util.List;

public interface IAuditLogRepository {
    long createAuditLog(AuditLog auditLog);

    List<AuditLog> getAuditLogs(AuditLogQueryParams queryParams);

    AuditLog getAuditLogById(String auditLogId);
}
