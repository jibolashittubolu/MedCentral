package com.example.v_medcentral.feature.staff.mapper;

import com.example.v_medcentral.model.entity.Staff;
import com.example.v_medcentral.model.request.CreateStaffRequest;
import com.example.v_medcentral.model.request.UpdateStaffRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffRequestMapper {

    Staff toEntity(CreateStaffRequest request);
    Staff toEntity(UpdateStaffRequest request);
}
