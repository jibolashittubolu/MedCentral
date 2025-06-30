package com.example.medcentral.feature.hospital.controller;

import com.example.medcentral.feature.hospital.model.request.HospitalCreateRequest;
import com.example.medcentral.feature.hospital.model.request.HospitalQueryParams;
import com.example.medcentral.feature.hospital.model.request.HospitalUpdateRequest;
import com.example.medcentral.feature.hospital.model.response.HospitalResponse;
import com.example.medcentral.feature.hospital.service.HospitalService;
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
@RequestMapping("/v1/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    @PostMapping("/createHospital")
    public ResponseEntity<BaseResponse<Long>> createHospital(@Valid @RequestBody HospitalCreateRequest request) {
        long rowsAffected = hospitalService.createHospital(request);

        String message = "A new hospital has been created successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.CREATED.getStatusCode())
                .responseCodeDescription(CustomResponseCode.CREATED.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getHospitals")
    public ResponseEntity<BaseResponse<List<HospitalResponse>>> getHospitals(@Valid HospitalQueryParams queryParams) {
        List<HospitalResponse> hospitals = hospitalService.getHospitals(queryParams);

        BaseResponse<List<HospitalResponse>> response = new BaseResponse.Builder<List<HospitalResponse>>()
                .responseCode(HttpStatus.OK.value())
                .responseCodeDescription("Success")
                .responseMessage("Hospitals retrieved successfully")
                .data(hospitals)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{hospitalId}")
    public ResponseEntity<BaseResponse<HospitalResponse>> getHospitalById(@PathVariable String hospitalId) {
        HospitalResponse hospital = hospitalService.getHospitalById(hospitalId);

        BaseResponse<HospitalResponse> response = new BaseResponse.Builder<HospitalResponse>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Hospital found")
                .data(hospital)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{hospitalId}")
    public ResponseEntity<BaseResponse<Long>> updateHospitalById(
            @PathVariable String hospitalId,
            @Valid @RequestBody HospitalUpdateRequest request) {


        // Inject the path variable into the request object
        request.setHospitalId(hospitalId);
        long rowsAffected = hospitalService.updateHospitalById(request);

        String message = "A new hospital has been created successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{hospitalId}")
    public ResponseEntity<BaseResponse<Long>> deleteHospitalById(@PathVariable String hospitalId) {
        long rowsAffected = hospitalService.deleteHospitalById(hospitalId);

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage("Hospital deleted successfully")
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
