package com.example.medcentral.feature.checkinstaff.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInStaffResponse {

    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
    private UUID staffId;
}
