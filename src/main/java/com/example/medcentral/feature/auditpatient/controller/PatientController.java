package com.example.medcentral.feature.auditpatient.controller;

import com.example.medcentral.feature.auditpatient.model.request.CreatePatientRequest;
import com.example.medcentral.feature.auditpatient.model.request.UpdatePatientRequest;
import com.example.medcentral.feature.auditpatient.model.response.PatientAPIResponse;
import com.example.medcentral.feature.auditpatient.model.response.PatientResponse;
import com.example.medcentral.feature.auditpatient.service.PatientService;
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
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create-patient")
    public ResponseEntity<PatientAPIResponse<String>> createPatient(@Valid @RequestBody CreatePatientRequest request,
                                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            PatientAPIResponse<String> errorResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("400")
                    .patientAPIResponseMessage("Validation failed")
                    .patientAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            patientService.createPatient(request);

            PatientAPIResponse<String> successResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("200")
                    .patientAPIResponseMessage("Patient created successfully")
                    .build();

            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            PatientAPIResponse<String> errorResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("500")
                    .patientAPIResponseMessage("ERROR")
                    .patientAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/update-patient")
    public ResponseEntity<PatientAPIResponse<String>> updateStudent(@Valid @RequestBody UpdatePatientRequest request,
                                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            PatientAPIResponse<String> errorResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("400")
                    .patientAPIResponseMessage("Validation failed")
                    .patientAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            patientService.updatePatient(request);

            PatientAPIResponse<String> successResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("200")
                    .patientAPIResponseMessage("Patient updated successfully")
                    .build();

            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            PatientAPIResponse<String> errorResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("500")
                    .patientAPIResponseMessage("ERROR")
                    .patientAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/get-patient-by-id/{patientId}")
    public ResponseEntity<PatientAPIResponse<Object>> getPatientById(@PathVariable("patientId") UUID patientId) {
        try {
            PatientResponse patient = patientService.getPatientById(patientId);

            PatientAPIResponse<Object> response = PatientAPIResponse.<Object>builder()
                    .patientAPIResponseCode("200")
                    .patientAPIResponseMessage("SUCCESS")
                    .patientAPIResponseData(patient)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            PatientAPIResponse<Object> errorResponse = PatientAPIResponse.<Object>builder()
                    .patientAPIResponseCode("500")
                    .patientAPIResponseMessage("ERROR")
                    .patientAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/get-all-patients")
    public ResponseEntity<PatientAPIResponse<Object>> getAllPatients() {
        try {
            List<PatientResponse> patientResponse = patientService.getAllPatients();

            PatientAPIResponse<Object> response = PatientAPIResponse.<Object>builder()
                    .patientAPIResponseCode("200")
                    .patientAPIResponseMessage("SUCCESS")
                    .patientAPIResponseData(patientResponse)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            PatientAPIResponse<Object> errorResponse = PatientAPIResponse.<Object>builder()
                    .patientAPIResponseCode("500")
                    .patientAPIResponseMessage("ERROR")
                    .patientAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/get-patient-by-search-query/{searchQuery}")
    public ResponseEntity<PatientAPIResponse<Object>> getPatientBySearchQuery(@PathVariable("searchQuery") String searchQuery) {
        try {
            List<PatientResponse> patientResponse = patientService.getPatientBySearchQuery(searchQuery);

            PatientAPIResponse<Object> response = PatientAPIResponse.<Object>builder()
                    .patientAPIResponseCode("200")
                    .patientAPIResponseMessage("SUCCESS")
                    .patientAPIResponseData(patientResponse)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            PatientAPIResponse<Object> errorResponse = PatientAPIResponse.<Object>builder()
                    .patientAPIResponseCode("500")
                    .patientAPIResponseMessage("ERROR")
                    .patientAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/delete-patient-by-id/{patientId}")
    public ResponseEntity<PatientAPIResponse<String>> deletePatientById(@PathVariable("patientId") UUID patientId) {
        try {
            patientService.deletePatientById(patientId);

            PatientAPIResponse<String> response = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("200")
                    .patientAPIResponseMessage("Patient deleted successfully")
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            PatientAPIResponse<String> errorResponse = PatientAPIResponse.<String>builder()
                    .patientAPIResponseCode("500")
                    .patientAPIResponseMessage("ERROR")
                    .patientAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
