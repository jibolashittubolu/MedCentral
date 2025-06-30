package com.example.medcentral.feature.checkinstaff.controller;

import com.example.medcentral.feature.checkinstaff.model.response.CheckInStaffAPIResponse;
import com.example.medcentral.feature.checkinstaff.model.response.CheckInStaffResponse;
import com.example.medcentral.feature.checkinstaff.service.CheckInStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/checkin-staff")
public class CheckInStaffController {

    private CheckInStaffService checkInStaffService;

    @Autowired
    public CheckInStaffController(CheckInStaffService checkInStaffService) {
        this.checkInStaffService = checkInStaffService;
    }

    @PostMapping("/assign-checkin-staff")
    public ResponseEntity<CheckInStaffAPIResponse<String>> assignCheckInStaff(@RequestParam UUID checkInId,
                                                                              @RequestParam UUID hospitalId,
                                                                              @RequestParam UUID staffId) {

        try {
            checkInStaffService.assignCheckInStaff(checkInId, hospitalId, staffId);

            CheckInStaffAPIResponse<String> response = CheckInStaffAPIResponse.<String>builder()
                    .checkInStaffAPIResponseCode("200")
                    .checkInStaffAPIResponseMessage("Check-in Staff Assigned")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            CheckInStaffAPIResponse<String> response = CheckInStaffAPIResponse.<String>builder()
                    .checkInStaffAPIResponseCode("500")
                    .checkInStaffAPIResponseMessage("ERROR")
                    .checkInStaffAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/assign-multiple-staff")
    public ResponseEntity<CheckInStaffAPIResponse<String>> assignMultipleStaffToCheckIn(@RequestParam UUID checkInId,
                                                                                        @RequestParam UUID hospitalId,
                                                                                        @RequestParam List<UUID> staffIds) {

        try {
            checkInStaffService.assignMultipleStaffToCheckIn(checkInId, hospitalId, staffIds);

            CheckInStaffAPIResponse<String> response = CheckInStaffAPIResponse.<String>builder()
                    .checkInStaffAPIResponseCode("200")
                    .checkInStaffAPIResponseMessage("All Check-in Staff Assigned")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            CheckInStaffAPIResponse<String> response = CheckInStaffAPIResponse.<String>builder()
                    .checkInStaffAPIResponseCode("500")
                    .checkInStaffAPIResponseMessage("ERROR")
                    .checkInStaffAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list-staff-in-checkin")
    public ResponseEntity<CheckInStaffAPIResponse<Object>> listCheckInStaff(@RequestParam UUID checkInId,
                                                                            @RequestParam UUID hospitalId) {

        try {
            List<CheckInStaffResponse> checkInStaffResponses = checkInStaffService.listCheckInStaff(checkInId, hospitalId);

            CheckInStaffAPIResponse<Object> response = CheckInStaffAPIResponse.<Object>builder()
                    .checkInStaffAPIResponseCode("200")
                    .checkInStaffAPIResponseMessage("SUCCESS")
                    .checkInStaffAPIResponseData(checkInStaffResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            CheckInStaffAPIResponse<Object> response = CheckInStaffAPIResponse.<Object>builder()
                    .checkInStaffAPIResponseCode("500")
                    .checkInStaffAPIResponseMessage("ERROR")
                    .checkInStaffAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
