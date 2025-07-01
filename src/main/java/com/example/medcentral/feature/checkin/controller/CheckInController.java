package com.example.medcentral.feature.checkin.controller;

import com.example.medcentral.feature.checkin.model.request.CreateCheckInRequest;
import com.example.medcentral.feature.checkin.model.response.CheckInAPIResponse;
import com.example.medcentral.feature.checkin.model.response.CheckInResponse;
import com.example.medcentral.feature.checkin.service.CheckInService;
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
@RequestMapping("/v1/checkin")
public class CheckInController {

    private CheckInService checkInService;

    @Autowired
    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping("/create-checkin")
    public ResponseEntity<CheckInAPIResponse<String>> createCheckin(@Valid @RequestBody CreateCheckInRequest request,
                                                                    @RequestParam UUID staffId,
                                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            CheckInAPIResponse<String> errorResponse = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode("400")
                    .checkInAPIResponseMessage("Validation failed")
                    .checkInAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            checkInService.createCheckin(request,staffId);

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(200))
                    .checkInAPIResponseMessage("Checkin created successfully")
                    .build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(500))
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/create-checkin-from-appointment")
    public ResponseEntity<CheckInAPIResponse<String>> createCheckInFromAppointment(@RequestParam UUID appointmentId,
                                                                                   @RequestParam UUID staffId) {

        try {
            checkInService.createCheckInFromAppointment(appointmentId,staffId);

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(200))
                    .checkInAPIResponseMessage("Checkin created successfully")
                    .build();

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(500))
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/update-checkin-dtatus-and-diagnosis")
    public ResponseEntity<CheckInAPIResponse<String>> updateCheckInStatusAndDiagnosis(@RequestParam String checkinStatus,
                                                                                      @RequestParam String checkInDiagnosis,
                                                                                      @RequestParam UUID staffId,
                                                                                      @RequestParam UUID checkInId,
                                                                                      @RequestParam UUID hospitalId) {

        try {
            checkInService.updateCheckInStatusAndDiagnosis(checkInDiagnosis, checkInId, staffId, checkinStatus, hospitalId);

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(200))
                    .checkInAPIResponseMessage("Checkin updated successfully")
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(500))
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/add-patient-notes")
    public ResponseEntity<CheckInAPIResponse<String>> addCheckInPatientNotes(@RequestParam String patientNotes,
                                                         @RequestParam UUID checkInId,
                                                         @RequestParam UUID hospitalId,
                                                         @RequestParam UUID staffId) {

        try {
            checkInService.addCheckInPatientNotes(checkInId, hospitalId, patientNotes,staffId);

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(200))
                    .checkInAPIResponseMessage("Notes added successfully")
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {

            CheckInAPIResponse<String> response = CheckInAPIResponse.<String>builder()
                    .checkInAPIResponseCode(String.valueOf(500))
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-check-in-by-id")
    public ResponseEntity<CheckInAPIResponse<Object>> getCheckInById(@RequestParam UUID checkInId,
                                                                      @RequestParam UUID hospitalId) {
        try {
            CheckInResponse checkInResponse = checkInService.getCheckInById(checkInId, hospitalId);

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("200")
                    .checkInAPIResponseMessage("SUCCESS")
                    .checkInAPIResponseData(checkInResponse)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("500")
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-all-checkins")
    public ResponseEntity<CheckInAPIResponse<Object>> getAllCheckins(@RequestParam UUID hospitalId) {

        try {
            List<CheckInResponse> checkInResponses = checkInService.getAllCheckIns(hospitalId);

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("200")
                    .checkInAPIResponseMessage("SUCCESS")
                    .checkInAPIResponseData(checkInResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("500")
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/gat-all-checkin-by-patient-id")
    public ResponseEntity<Object> getAllCheckInsByPatientId(@RequestParam UUID hospitalId,
                                                                           @RequestParam UUID patientId) {

        try {
            List<CheckInResponse> checkInResponses = checkInService.getAllCheckInsByPatientId(hospitalId, patientId);

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("200")
                    .checkInAPIResponseMessage("SUCCESS")
                    .checkInAPIResponseData(checkInResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("500")
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-active-checkins-by-patient-id")
    public ResponseEntity<Object> getActiveCheckInsByPatientId(@RequestParam UUID hospitalId,
                                                                              @RequestParam UUID patientId) {

        try {
            List<CheckInResponse> checkInResponses = checkInService.getActiveCheckInsByPatientId(hospitalId, patientId);

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("200")
                    .checkInAPIResponseMessage("SUCCESS")
                    .checkInAPIResponseData(checkInResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("500")
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list-checkin-for-staff")
    public ResponseEntity<Object> listAllCheckInStaffParticipatedIn(@RequestParam UUID staffId,
                                                                    @RequestParam UUID hospitalId) {
        try {
            List<CheckInResponse> checkInResponses = checkInService.listAllCheckInStaffParticipatedIn(hospitalId, staffId);

            CheckInAPIResponse<Object> response = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("200")
                    .checkInAPIResponseMessage("SUCCESS")
                    .checkInAPIResponseData(checkInResponses)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            CheckInAPIResponse<Object> errorResponse = CheckInAPIResponse.<Object>builder()
                    .checkInAPIResponseCode("500")
                    .checkInAPIResponseMessage("ERROR")
                    .checkInAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
