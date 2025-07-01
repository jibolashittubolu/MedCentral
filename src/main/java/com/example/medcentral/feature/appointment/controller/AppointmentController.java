package com.example.v_medcentral.feature.appointment.controller;

import com.example.v_medcentral.model.request.CreateAppointmentRequest;
import com.example.v_medcentral.model.request.UpdateAppointmentRequest;
import com.example.v_medcentral.model.response.AppointmentAPIResponse;
import com.example.v_medcentral.model.response.AppointmentResponse;
import com.example.v_medcentral.service.AppointmentService;
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
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create-appointment")
    public ResponseEntity<AppointmentAPIResponse<String>> createAppointment(@Valid @RequestBody CreateAppointmentRequest request,
                                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            AppointmentAPIResponse<String> errorResponse = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("400")
                    .appointmentAPIResponseMessage("Validation failed")
                    .appointmentAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            appointmentService.createAppointment(request);

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("Appointment created successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<String> errorResponse = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PatchMapping("/update-appointment")
    public ResponseEntity<AppointmentAPIResponse<String>> updateAppointment(@Valid @RequestBody UpdateAppointmentRequest request,
                                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("400")
                    .appointmentAPIResponseMessage("Validation failed")
                    .appointmentAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(response);
        }

        try {
            appointmentService.updateAppointment(request);

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("Appointment updated successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/reschedule-appointment")
    public ResponseEntity<AppointmentAPIResponse<String>> rescheduleAppointment(@Valid @RequestBody UpdateAppointmentRequest request,
                                                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            AppointmentAPIResponse<String> errorResponse = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("400")
                    .appointmentAPIResponseMessage("Validation failed")
                    .appointmentAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            appointmentService.rescheduleAppointment(request);

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("Appointment rescheduled successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/cancel-appointment")
    public ResponseEntity<AppointmentAPIResponse<String>> cancelAppointment(@RequestParam UUID appointmentId) {

        try {
            appointmentService.cancelAppointment(appointmentId);

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("Appointment cancelled successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete-appointment")
    public ResponseEntity<AppointmentAPIResponse<String>> deleteAppointment(@RequestParam UUID appointmentId) {

        try {
            appointmentService.deleteAppointment(appointmentId);

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("Appointment deleted successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<String> response = AppointmentAPIResponse.<String>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-appointment-by-id")
    public ResponseEntity<AppointmentAPIResponse<Object>> getAppointmentById(@RequestParam UUID appointmentId) {

        try {
            AppointmentResponse appointmentResponse = appointmentService.getAppointmentById(appointmentId);

            AppointmentAPIResponse<Object> response = AppointmentAPIResponse.<Object>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("SUCCESS")
                    .appointmentAPIResponseData(appointmentResponse)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<Object> response = AppointmentAPIResponse.<Object>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-appointment-by-patient-id")
    public ResponseEntity<AppointmentAPIResponse<Object>> getAppointmentByPatientId(@RequestParam UUID appointmentId) {

        try {
            List<AppointmentResponse> appointmentResponseList = appointmentService.getAppointmentByPatientId(appointmentId);

            AppointmentAPIResponse<Object> response = AppointmentAPIResponse.<Object>builder()
                    .appointmentAPIResponseCode("200")
                    .appointmentAPIResponseMessage("SUCCESS")
                    .appointmentAPIResponseData(appointmentResponseList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            AppointmentAPIResponse<Object> response = AppointmentAPIResponse.<Object>builder()
                    .appointmentAPIResponseCode("500")
                    .appointmentAPIResponseMessage("ERROR")
                    .appointmentAPIResponseData(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
