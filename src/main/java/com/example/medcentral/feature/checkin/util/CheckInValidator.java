package com.example.medcentral.feature.checkin.util;

import com.example.medcentral.feature.checkin.model.response.CheckInResponse;

public class CheckInValidator {
    public static void validate(CheckInResponse checkIn) {
        if (checkIn == null) {
            throw new IllegalArgumentException("Check-in not found.");
        }
        if ("DELETED".equalsIgnoreCase(checkIn.getCheckInStatus())) {
            throw new IllegalArgumentException("Check-in is DELETED.");
        }
    }
}
