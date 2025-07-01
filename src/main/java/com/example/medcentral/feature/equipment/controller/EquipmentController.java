package com.example.medcentral.feature.equipment.controller;


import com.example.medcentral.feature.equipment.model.request.EquipmentCreateRequest;
import com.example.medcentral.feature.equipment.model.request.EquipmentQueryParams;
import com.example.medcentral.feature.equipment.model.request.EquipmentUpdateRequest;
import com.example.medcentral.feature.equipment.model.response.EquipmentResponse;
import com.example.medcentral.feature.equipment.service.EquipmentService;
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
@RequestMapping("/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }


    @PostMapping("/createEquipment/{hospitalId}")
    public ResponseEntity<BaseResponse<Long>> createEquipment(
            @PathVariable String hospitalId,
            @Valid @RequestBody EquipmentCreateRequest request
    ) {
        // Inject the path variable into the request object
        request.setEquipmentHospitalId(hospitalId);

        long rowsAffected = equipmentService.createEquipment(request);

        String message = "A new equpment has been created successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.CREATED.getStatusCode())
                .responseCodeDescription(CustomResponseCode.CREATED.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getEquipments")
    public ResponseEntity<BaseResponse<List<EquipmentResponse>>> getEquipments(@Valid EquipmentQueryParams queryParams) {
        List<EquipmentResponse> equipments = equipmentService.getEquipments(queryParams);

        BaseResponse<List<EquipmentResponse>> response = new BaseResponse.Builder<List<EquipmentResponse>>()
                .responseCode(HttpStatus.OK.value())
                .responseCodeDescription("Success")
                .responseMessage("Equipments retrieved successfully")
                .data(equipments)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{equipmentId}")
    public ResponseEntity<BaseResponse<EquipmentResponse>> getEquipmentById(@PathVariable String equipmentId) {
        EquipmentResponse equipment = equipmentService.getEquipmentById(equipmentId);

        BaseResponse<EquipmentResponse> response = new BaseResponse.Builder<EquipmentResponse>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Equipment found")
                .data(equipment)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{equipmentId}")
    public ResponseEntity<BaseResponse<Long>> updateEquipmentById(
            @PathVariable String equipmentId,
            @Valid @RequestBody EquipmentUpdateRequest request) {


        // Inject the path variable into the request object
        request.setEquipmentHospitalId(equipmentId);
        long rowsAffected = equipmentService.updateEquipmentById(request);

        String message = "Equipment has been updated successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage(message)
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<BaseResponse<Long>> deleteEquipmentById(@PathVariable String equipmentId) {
        long rowsAffected = equipmentService.deleteEquipmentById(equipmentId);

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage("Equipment deleted successfully")
                .data(rowsAffected)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
