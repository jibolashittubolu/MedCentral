package com.example.medcentral.feature.checkinstaff.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInStaffAPIResponse<T> {

    private String checkInStaffAPIResponseCode;
    private String checkInStaffAPIResponseMessage;
    private T checkInStaffAPIResponseData;
}