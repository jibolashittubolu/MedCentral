package com.example.medcentral.feature.hospital.mapper;


import com.example.medcentral.feature.auditlog.model.entity.AuditLog;
import com.example.medcentral.feature.auditlog.model.response.AuditLogResponse;
import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.model.response.HospitalResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IHospitalMapper {

    //    @Mapping(target = "studentCreatedAt", source = "studentCreatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
//    @Mapping(target = "studentUpdatedAt", source = "studentUpdatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    HospitalResponse toResponse(Hospital hospital);
//    Student toEntity(StudentCreateRequest request);
}
