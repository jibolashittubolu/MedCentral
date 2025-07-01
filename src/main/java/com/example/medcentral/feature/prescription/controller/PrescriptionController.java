package com.example.medcentral.feature.prescription.controller;


import com.example.medcentral.feature.prescription.model.entity.Prescription;
import com.example.medcentral.feature.prescription.model.request.PrescriptionCreateRequest;
import com.example.medcentral.feature.prescription.model.request.PrescriptionQueryParams;
import com.example.medcentral.feature.prescription.model.request.PrescriptionUpdateRequest;
import com.example.medcentral.feature.prescription.model.response.PrescriptionResponse;
import com.example.medcentral.feature.prescription.service.PrescriptionService;
import com.example.medcentral.model.response.BaseResponse;
import com.example.medcentral.model.response.CustomResponseCode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Validated  // enable @Min on @RequestParam
@RestController
@RequestMapping("/v1/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {

        this.prescriptionService = prescriptionService;
    }


    @PostMapping("/createPrescription")
    public ResponseEntity<BaseResponse<Long>> createPrescription(
            @Valid @RequestBody PrescriptionCreateRequest request
    ) {
        long rowsAffected = prescriptionService.createPrescription(request);

        String message = "A new prescription has been created successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.CREATED.getStatusCode())
                .responseCodeDescription(CustomResponseCode.CREATED.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getPrescriptions")
    public ResponseEntity<BaseResponse<List<PrescriptionResponse>>> getPrescriptions(@Valid PrescriptionQueryParams queryParams) {
        List<PrescriptionResponse> prescriptions = prescriptionService.getPrescriptions(queryParams);

        BaseResponse<List<PrescriptionResponse>> response = new BaseResponse.Builder<List<PrescriptionResponse>>()
                .responseCode(HttpStatus.OK.value())
                .responseCodeDescription("Success")
                .responseMessage("Prescriptions retrieved successfully")
                .data(prescriptions)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{prescriptionId}")
    public ResponseEntity<BaseResponse<PrescriptionResponse>> getPrescrictionById(@PathVariable String prescriptionId) {
        PrescriptionResponse prescription = prescriptionService.getPrescriptionById(prescriptionId);

        BaseResponse<PrescriptionResponse> response = new BaseResponse.Builder<PrescriptionResponse>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Prescription found")
                .data(prescription)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{prescriptionId}")
    public ResponseEntity<BaseResponse<Long>> updatePrescriptionById(
            @PathVariable String prescriptionId,
            @Valid @RequestBody PrescriptionUpdateRequest request) {


        // Inject the path variable into the request object
        request.setPrescriptionId(prescriptionId);
        long rowsAffected = prescriptionService.updatePrescriptionById(request);

        String message = "Prescription has been updated successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<BaseResponse<Long>> deletePrescriptionById(@PathVariable String prescriptionId) {
        long rowsAffected = prescriptionService.deletePrescriptionById(prescriptionId);

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage("Prescription deleted successfully")
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
