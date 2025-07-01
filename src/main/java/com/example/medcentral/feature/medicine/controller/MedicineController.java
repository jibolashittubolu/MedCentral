package com.example.medcentral.feature.medicine.controller;

import com.example.medcentral.feature.medicine.model.request.CreateMedicineRequest;
import com.example.medcentral.feature.medicine.model.request.UpdateMedicineRequest;
import com.example.medcentral.feature.medicine.model.response.MedicineAPIResponse;
import com.example.medcentral.feature.medicine.model.response.MedicineResponse;
import com.example.medcentral.feature.medicine.service.MedicineService;
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
@RequestMapping("/v1/medicine")
public class MedicineController {

    private MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/create-medicine")
    public ResponseEntity<MedicineAPIResponse<String>> createMedicine(@Valid @RequestBody CreateMedicineRequest request,
                                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            MedicineAPIResponse<String> errorResponse = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("400")
                    .medicineAPIResponseMessage("Validation failed")
                    .medicineAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            medicineService.createMedicine(request);

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("Medicine created successfully")
                    .build();

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/update-medicine")
    public ResponseEntity<MedicineAPIResponse<String>> updateMedicine(@Valid @RequestBody UpdateMedicineRequest request,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            MedicineAPIResponse<String> errorResponse = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("400")
                    .medicineAPIResponseMessage("Validation failed")
                    .medicineAPIResponseData(errors.toString())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            medicineService.updateMedicine(request);

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("Medicine updated successfully")
                    .build();

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/update-medicine-quantity")
    public ResponseEntity<MedicineAPIResponse<String>> updateQuantity(@RequestParam int medicineQuantity,
                                                 @RequestParam UUID hospitalId,
                                                 @RequestParam UUID medicineId) {

        try {
            medicineService.updateMedicineQuantity(medicineQuantity, hospitalId, medicineId);

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("Medicine updated successfully")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-medicine-by-id")
    public ResponseEntity<MedicineAPIResponse<Object>> getMedicineById(@RequestParam UUID hospitalId,
                                                                       @RequestParam UUID medicineId) {

        try {
            MedicineResponse medicineResponse = medicineService.getMedicineById(medicineId, hospitalId);

            MedicineAPIResponse<Object> response = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("SUCCESS")
                    .medicineAPIResponseData(medicineResponse)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            MedicineAPIResponse<Object> response = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-medicine-by-hospital-id")
    public ResponseEntity<MedicineAPIResponse<Object>> getMedicineByHospitalId(@RequestParam UUID hospitalId) {

        try {
            List<MedicineResponse> medicineResponses =  medicineService.getMedicineByHospitalId(hospitalId);

            MedicineAPIResponse<Object> response = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("SUCCESS")
                    .medicineAPIResponseData(medicineResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {

            MedicineAPIResponse<Object> response = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get-medicine-with-low-stock")
    public ResponseEntity<MedicineAPIResponse<Object>> getMedicineWithLowStock(@RequestParam UUID hospitalId) {
        try {
            List<MedicineResponse> medicineResponses = medicineService.getMedicineWithLowStock(hospitalId);

            MedicineAPIResponse<Object> response = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("SUCCESS")
                    .medicineAPIResponseData(medicineResponses)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            MedicineAPIResponse<Object> errorResponse = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/get-medicine-by-name")
    public ResponseEntity<MedicineAPIResponse<Object>> getMedicineByName(@RequestParam String medicineName,
                                                                         @RequestParam UUID hospitalId) {
        try {
            List<MedicineResponse> medicineResponses = medicineService.getMedicineByName(medicineName, hospitalId);

            MedicineAPIResponse<Object> response = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("SUCCESS")
                    .medicineAPIResponseData(medicineResponses)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            MedicineAPIResponse<Object> errorResponse = MedicineAPIResponse.<Object>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/delete-medicine")
    public ResponseEntity<MedicineAPIResponse<String>> deleteMedicineById(@RequestParam UUID medicineId,
                                                                          @RequestParam UUID hospitalId) {
        try {
            medicineService.deleteMedicineById(medicineId, hospitalId);

            MedicineAPIResponse<String> response = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("200")
                    .medicineAPIResponseMessage("Medicine deleted successfully")
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            MedicineAPIResponse<String> errorResponse = MedicineAPIResponse.<String>builder()
                    .medicineAPIResponseCode("500")
                    .medicineAPIResponseMessage("ERROR")
                    .medicineAPIResponseData(ex.toString())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
