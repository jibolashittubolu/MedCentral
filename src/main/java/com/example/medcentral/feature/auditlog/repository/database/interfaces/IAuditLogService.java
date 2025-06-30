package com.example.medcentral.feature.auditlog.repository.database.interfaces;

import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.request.AuditLogCreateRequest;
import com.example.medcentral.feature.auditlog.model.request.AuditLogQueryParams;
import com.example.medcentral.feature.auditlog.model.response.AuditLogResponse;


import java.util.List;


public interface IAuditLogService {
    //createAuditLog
    long createAuditLog(AuditLogCreateRequest request);
    //should take in request or so as we do in TS. Modify later

    //   getAuditLogs
    List<AuditLogResponse> getAuditLogs(AuditLogQueryParams queryParams);

    //getAuditLogById
    AuditLogResponse getAuditLogById(String auditLogId);

}


