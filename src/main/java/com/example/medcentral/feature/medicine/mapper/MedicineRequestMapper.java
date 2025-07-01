package com.example.medcentral.feature.medicine.mapper;

import com.example.medcentral.feature.medicine.model.entity.Medicine;
import com.example.medcentral.feature.medicine.model.request.CreateMedicineRequest;
import com.example.medcentral.feature.medicine.model.request.UpdateMedicineRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicineRequestMapper {

    Medicine toEntity(CreateMedicineRequest request);
    Medicine toEntity(UpdateMedicineRequest request);
}
