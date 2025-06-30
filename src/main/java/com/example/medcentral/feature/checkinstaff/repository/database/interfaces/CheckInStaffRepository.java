package com.example.medcentral.feature.checkinstaff.repository.database.interfaces;

import com.example.medcentral.feature.checkinstaff.model.response.CheckInStaffResponse;

import java.util.List;
import java.util.UUID;

public interface CheckInStaffRepository {

    int assignStaffToCheckIn(UUID checkInId, UUID staffId);
    int[] assignMultipleStaffToCheckIn(UUID checkInId, UUID hospitalId, List<UUID> staffIds);
    List<CheckInStaffResponse> listAllCheckInStaff(UUID checkInId, UUID hospitalId);
}