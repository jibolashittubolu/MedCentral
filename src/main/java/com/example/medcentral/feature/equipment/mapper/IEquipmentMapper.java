package com.example.medcentral.feature.equipment.mapper;


import com.example.medcentral.feature.equipment.model.entity.Equipment;
import com.example.medcentral.feature.equipment.model.response.EquipmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEquipmentMapper {

    //    @Mapping(target = "studentCreatedAt", source = "studentCreatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
//    @Mapping(target = "studentUpdatedAt", source = "studentUpdatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    EquipmentResponse toResponse(Equipment equipment);
//    Student toEntity(StudentCreateRequest request);
}
