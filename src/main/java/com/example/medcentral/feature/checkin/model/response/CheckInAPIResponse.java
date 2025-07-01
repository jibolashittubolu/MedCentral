package com.example.medcentral.feature.checkin.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInAPIResponse<T> {

    private String checkInAPIResponseCode;
    private String checkInAPIResponseMessage;
    private T checkInAPIResponseData;

}
