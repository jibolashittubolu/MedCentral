package com.example.medcentral.feature.auditlog.mapper;


import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.response.AuditLogResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuditLogMapper {

    //    @Mapping(target = "studentCreatedAt", source = "studentCreatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
//    @Mapping(target = "studentUpdatedAt", source = "studentUpdatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    AuditLogResponse toResponse(AuditLog auditLog);
//    Student toEntity(StudentCreateRequest request);
}
