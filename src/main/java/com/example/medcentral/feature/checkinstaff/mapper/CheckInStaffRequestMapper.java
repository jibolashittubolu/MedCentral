package com.example.medcentral.feature.checkinstaff.mapper;

import com.example.medcentral.feature.checkinstaff.model.entity.CheckInStaff;
import com.example.medcentral.feature.checkinstaff.model.request.CreateCheckInStaffRequest;
import com.example.medcentral.feature.checkinstaff.model.request.UpdateCheckInStaffRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckInStaffRequestMapper {

    CheckInStaff toEntity(CreateCheckInStaffRequest request);
    CheckInStaff toEntity(UpdateCheckInStaffRequest request);
}
