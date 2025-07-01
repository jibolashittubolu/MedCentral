package com.example.medcentral.feature.equipment.repository.database.interfaces;



import com.example.medcentral.feature.equipment.model.request.EquipmentCreateRequest;
import com.example.medcentral.feature.equipment.model.request.EquipmentQueryParams;
import com.example.medcentral.feature.equipment.model.request.EquipmentUpdateRequest;
import com.example.medcentral.feature.equipment.model.response.EquipmentResponse;

import java.util.List;

public interface IEquipmentService {
    //createEquipment
    long createEquipment(EquipmentCreateRequest request);
    //should take in request or so as we do in TS. Modify later

    //  getEquipments
    List<EquipmentResponse> getEquipments(EquipmentQueryParams queryParams);

    //getEquipmentById
    EquipmentResponse getEquipmentById(String equipmentId);

    long updateEquipmentById(EquipmentUpdateRequest request);

    long deleteEquipmentById(String equipmentId);
}


