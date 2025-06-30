package com.example.medcentral.feature.auditpatient.mapper;

import com.example.medcentral.feature.auditpatient.model.entity.Patient;
import com.example.medcentral.feature.auditpatient.model.request.CreatePatientRequest;
import com.example.medcentral.feature.auditpatient.model.request.UpdatePatientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientRequestMapper {
    Patient toEntity(CreatePatientRequest request);
    Patient toEntity(UpdatePatientRequest request);
}
