package com.example.medcentral.feature.checkin.mapper;

import com.example.medcentral.feature.checkin.model.entity.CheckIn;
import com.example.medcentral.feature.checkin.model.request.CreateCheckInRequest;
import com.example.medcentral.feature.checkin.model.request.UpdateCheckInRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckInRequestMapper {

    CheckIn toEntity(CreateCheckInRequest request);
    CheckIn toEntity(UpdateCheckInRequest request);
}
