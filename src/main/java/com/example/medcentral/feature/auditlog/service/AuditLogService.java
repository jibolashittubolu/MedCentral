package com.example.medcentral.feature.auditlog.service;

import com.example.medcentral.exception.ResourceNotFoundException;
import com.example.medcentral.feature.auditlog.mapper.IAuditLogMapper;
import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.request.AuditLogCreateRequest;
import com.example.medcentral.feature.auditlog.model.request.AuditLogQueryParams;
import com.example.medcentral.feature.auditlog.model.response.AuditLogResponse;
import com.example.medcentral.feature.auditlog.repository.database.interfaces.IAuditLogRepository;
import com.example.medcentral.feature.auditlog.repository.database.interfaces.IAuditLogService;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditLogService implements IAuditLogService {

    private final IAuditLogRepository auditLogRepository;
    private final IAuditLogMapper auditLogMapper;

    @Autowired
    public AuditLogService(
            IAuditLogRepository auditLogRepository,
            IAuditLogMapper auditLogMapper
    ) {
        this.auditLogRepository = auditLogRepository;
        this.auditLogMapper = auditLogMapper;
    }

    @Override
    public long createAuditLog(AuditLogCreateRequest request) {
        try {
            Gson gson = new Gson();
//        var myrequestbody = gson.toJson(request);
            //Gson is a package to help deal with JSON -> Convert to and from json
            var auditLog = gson.fromJson(gson.toJson(request), AuditLog.class);

            return auditLogRepository.createAuditLog(auditLog);
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<AuditLogResponse> getAuditLogs(AuditLogQueryParams queryParams) {
        try{
            return auditLogRepository
                    .getAuditLogs(queryParams)
                    .stream()
                    .map(auditLogMapper::toResponse)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public AuditLogResponse getAuditLogById(String auditLogId) {
        AuditLog auditLog = auditLogRepository.getAuditLogById(auditLogId);
        if (auditLog == null) {
            throw new ResourceNotFoundException("Audit log not found of id: " + auditLogId);
        }
        return auditLogMapper.toResponse(auditLog);
    }


}
