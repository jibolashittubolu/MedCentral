package com.example.medcentral.feature.auditlog.controller;

import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.request.AuditLogCreateRequest;
import com.example.medcentral.feature.auditlog.model.request.AuditLogQueryParams;
import com.example.medcentral.feature.auditlog.model.response.AuditLogResponse;
import com.example.medcentral.feature.auditlog.service.AuditLogService;

import com.example.medcentral.model.response.BaseResponse;
import com.example.medcentral.model.response.CustomResponseCode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Validated  // enable @Min on @RequestParam
@RestController
@RequestMapping("/v1/auditLog")
public class AuditLogController {

    private final AuditLogService auditLogService;

    @Autowired
    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }


    @PostMapping("/createAuditLog")
    public ResponseEntity<BaseResponse<Long>> createAuditLog(@Valid @RequestBody AuditLogCreateRequest request) {
        long rowsAffected = auditLogService.createAuditLog(request);

        String message = "A new auditLog has been created successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.CREATED.getStatusCode())
                .responseCodeDescription(CustomResponseCode.CREATED.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAuditLogs")
    public ResponseEntity<BaseResponse<List<AuditLogResponse>>> getAuditLogs(@Valid AuditLogQueryParams queryParams) {
        List<AuditLogResponse> auditLogs = auditLogService.getAuditLogs(queryParams);

        BaseResponse<List<AuditLogResponse>> response = new BaseResponse.Builder<List<AuditLogResponse>>()
                .responseCode(HttpStatus.OK.value())
                .responseCodeDescription("Success")
                .responseMessage("Audit logs retrieved successfully")
                .data(auditLogs)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{auditLogId}")
    public ResponseEntity<BaseResponse<AuditLogResponse>> getAuditLogById(@PathVariable String auditLogId) {
        AuditLogResponse auditLog = auditLogService.getAuditLogById(auditLogId);

        BaseResponse<AuditLogResponse> response = new BaseResponse.Builder<AuditLogResponse>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("auditLog found")
                .data(auditLog)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
