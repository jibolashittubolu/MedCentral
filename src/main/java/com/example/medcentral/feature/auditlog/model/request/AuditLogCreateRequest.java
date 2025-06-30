package com.example.medcentral.feature.auditlog.model.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class AuditLogCreateRequest {

    @NotBlank(message = "specify the httpMethod property e.g get, post, patch, put, delete")
    private String httpMethod;

    @NotBlank(message = "specify the description. e.g updated a record")
    private String description;

    @NotBlank(message = "specify the tableName e.g GROUP_1_APPOINTMENT")
    private String tableName;

    @NotBlank(message = "specify the recordId that was modified")
    private String recordId;

    @NotBlank(message = "specify the createdBy, i.e the userId of the actor")
    private String createdBy;

    private String oldValue;
    private String newValue;
    private String source;
    private String query;
    private String ipAddress;
    private String status;
    private String userAgent;
    private String createdAt;

}
