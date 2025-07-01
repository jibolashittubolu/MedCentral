package com.example.medcentral.feature.staff.util.validator;

import com.example.medcentral.feature.staff.model.entity.Staff;
import com.example.medcentral.feature.staff.model.response.StaffResponse;

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
