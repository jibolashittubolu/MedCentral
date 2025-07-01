package com.example.medcentral.feature.checkin.repository.database.interfaces;

import com.example.medcentral.feature.checkin.model.entity.CheckIn;
import com.example.medcentral.feature.checkin.model.response.CheckInResponse;

import java.util.List;
import java.util.UUID;

public interface CheckInRepository {

    int createCheckIn(CheckIn checkIn, UUID staffId);
    int createCheckInFromAppointment(UUID appointmentId, UUID staffId);
    int updateCheckInStatusAndDiagnosis(String checkInDiagnosis, UUID staffId, UUID checkInId, String checkInStatus, UUID hospitalId);
    int addCheckInPatientNotes(UUID checkInId, UUID hospitalId, String patientNotes, UUID staffId);
    CheckInResponse getCheckInById(UUID checkInId, UUID hospitalId);
    List<CheckInResponse> getAllCheckIns(UUID hospitalId);
    List<CheckInResponse> getAllCheckInsByPatientId(UUID hospitalId, UUID patientId);
    List<CheckInResponse> getActiveCheckInsByPatientId(UUID hospitalId, UUID patientId);
    List<CheckInResponse> listAllCheckInStaffParticipatedIn(UUID hospitalId, UUID staffId);
}
