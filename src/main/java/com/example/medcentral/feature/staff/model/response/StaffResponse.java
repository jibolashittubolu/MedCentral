package com.example.medcentral.feature.staff.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {

    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
    private String hospitalName;
    private String staffStatus;
}
