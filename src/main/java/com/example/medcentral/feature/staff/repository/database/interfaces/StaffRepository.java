package com.example.medcentral.feature.staff.repository.database.interfaces;

import com.example.medcentral.feature.staff.model.entity.Staff;
import com.example.medcentral.feature.staff.model.response.StaffResponse;

import java.util.List;
import java.util.UUID;

public interface StaffRepository {

    int createStaff(Staff staff);
    int updateStaff(Staff staff);
    StaffResponse getStaffById(UUID staffId);
    List<StaffResponse> getStaffByHosital(UUID staffHospitalId);
    int deleteStaff(UUID staffId);
}
