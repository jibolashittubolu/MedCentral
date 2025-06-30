package com.example.medcentral.feature.patient.mapper;

import com.example.medcentral.feature.patient.model.entity.Patient;
import com.example.medcentral.feature.patient.model.request.CreatePatientRequest;
import com.example.medcentral.feature.patient.model.request.UpdatePatientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientRequestMapper {
    Patient toEntity(CreatePatientRequest request);
    Patient toEntity(UpdatePatientRequest request);
}
