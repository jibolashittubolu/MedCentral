package com.example.v_medcentral.feature.staff.repository.database.interfaces;

import com.example.v_medcentral.model.entity.Staff;
import com.example.v_medcentral.model.response.StaffResponse;

import java.util.List;
import java.util.UUID;

public interface StaffRepository {

    int createStaff(Staff staff);
    int updateStaff(Staff staff);
    StaffResponse getStaffById(UUID staffId);
    List<StaffResponse> getStaffByHosital(UUID staffHospitalId);
    int deleteStaff(UUID staffId);
}
