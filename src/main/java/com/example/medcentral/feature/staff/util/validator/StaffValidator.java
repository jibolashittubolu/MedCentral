package com.example.v_medcentral.feature.staff.util.validator;

import com.example.v_medcentral.model.entity.Staff;
import com.example.v_medcentral.model.response.StaffResponse;

public class StaffValidator {

    public static void validate(Staff staff) {
        if (staff == null) {
            throw new IllegalArgumentException("Staff not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(staff.getStaffStatus())) {
            throw new IllegalArgumentException("Staff is not ACTIVE.");
        }
    }

    public static void validate(StaffResponse staff) {
        if (staff == null) {
            throw new IllegalArgumentException("Staff not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(staff.getStaffStatus())) {
            throw new IllegalArgumentException("Staff is not ACTIVE.");
        }
    }
}
