package com.example.medcentral.feature.prescription.mapper;


import com.example.medcentral.feature.prescription.model.entity.Prescription;
import com.example.medcentral.feature.prescription.model.response.PrescriptionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPrescriptionMapper {

    //    @Mapping(target = "studentCreatedAt", source = "studentCreatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
//    @Mapping(target = "studentUpdatedAt", source = "studentUpdatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    PrescriptionResponse toResponse(Prescription prescription);
//    Student toEntity(StudentCreateRequest request);
}
