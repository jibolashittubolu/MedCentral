package com.example.medcentral.feature.checkin.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInResponse {

    private String patientFirstName;
    private String patientLastName;

    private String hospitalName;

    private UUID checkInId;
    private String checkInReason;
    private String checkInDiagnosis;
    private String checkInStatus;
    private String checkInPatientNotes;

    private String staffAssigned;
}
