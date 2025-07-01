package com.example.medcentral.feature.staff.mapper;

import com.example.medcentral.feature.staff.model.entity.Staff;
import com.example.medcentral.feature.staff.model.request.CreateStaffRequest;
import com.example.medcentral.feature.staff.model.request.UpdateStaffRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffRequestMapper {

    Staff toEntity(CreateStaffRequest request);
    Staff toEntity(UpdateStaffRequest request);
}
