package com.example.medcentral.feature.staff.controller;

import com.example.medcentral.feature.staff.model.request.CreateStaffRequest;
import com.example.medcentral.feature.staff.model.request.UpdateStaffRequest;
import com.example.medcentral.feature.staff.model.response.StaffAPIResponse;
import com.example.medcentral.feature.staff.model.response.StaffResponse;
import com.example.medcentral.feature.staff.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create-staff")
    public ResponseEntity<StaffAPIResponse<String>> createStaff(@Valid @RequestBody CreateStaffRequest request,
                                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            StaffAPIResponse<String> errorResponse = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("400")
                    .staffAPIResponseMessage("Validation failed")
                    .staffAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            staffService.createStaff(request);

            StaffAPIResponse<String> response = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("200")
                    .staffAPIResponseMessage("Staff created successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            StaffAPIResponse<String> response = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("500")
                    .staffAPIResponseMessage("ERROR")
                    .staffAPIResponseData(ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/update-staff")
    public ResponseEntity<StaffAPIResponse<String>> updateStaff(@Valid @RequestBody UpdateStaffRequest request,
                                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            StaffAPIResponse<String> errorResponse = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("400")
                    .staffAPIResponseMessage("Validation failed")
                    .staffAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            staffService.updateStaff(request);

            StaffAPIResponse<String> response = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("200")
                    .staffAPIResponseMessage("Staff updated successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            StaffAPIResponse<String> response = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("500")
                    .staffAPIResponseMessage("ERROR")
                    .staffAPIResponseData(ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-staff-by-id")
    public ResponseEntity<StaffAPIResponse<Object>> getStaffById(@RequestParam UUID staffId) {

        try {
            StaffResponse staffResponse = staffService.getStaffById(staffId);

            StaffAPIResponse<Object> response = StaffAPIResponse.<Object>builder()
                    .staffAPIResponseCode("200")
                    .staffAPIResponseMessage("SUCCESS")
                    .staffAPIResponseData(staffResponse)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            StaffAPIResponse<Object> response = StaffAPIResponse.<Object>builder()
                    .staffAPIResponseCode("500")
                    .staffAPIResponseMessage("ERROR")
                    .staffAPIResponseData(ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-stff-by-hospital")
    private ResponseEntity<StaffAPIResponse<Object>> getStaffByHospital(@RequestParam UUID hospitalId) {

        try {
            List<StaffResponse> staffResponse = staffService.getStaffByHospital(hospitalId);

            StaffAPIResponse<Object> response = StaffAPIResponse.<Object>builder()
                    .staffAPIResponseCode("200")
                    .staffAPIResponseMessage("SUCCESS")
                    .staffAPIResponseData(staffResponse)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            StaffAPIResponse<Object> response = StaffAPIResponse.<Object>builder()
                    .staffAPIResponseCode("500")
                    .staffAPIResponseMessage("ERROR")
                    .staffAPIResponseData(ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete-staff")
    public ResponseEntity<StaffAPIResponse<String>> deleteStaff(@RequestParam UUID staffId) {
        try {
            staffService.deleteStaff(staffId);

            StaffAPIResponse<String> response = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("200")
                    .staffAPIResponseMessage("Staff deleted successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            StaffAPIResponse<String> response = StaffAPIResponse.<String>builder()
                    .staffAPIResponseCode("500")
                    .staffAPIResponseMessage("ERROR")
                    .staffAPIResponseData(ex.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
