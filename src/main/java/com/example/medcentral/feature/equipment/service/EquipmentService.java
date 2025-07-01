package com.example.medcentral.feature.equipment.service;

import com.example.medcentral.exception.ResourceNotFoundException;


import com.example.medcentral.feature.equipment.mapper.IEquipmentMapper;
import com.example.medcentral.feature.equipment.model.entity.Equipment;
import com.example.medcentral.feature.equipment.model.request.EquipmentCreateRequest;
import com.example.medcentral.feature.equipment.model.request.EquipmentQueryParams;
import com.example.medcentral.feature.equipment.model.request.EquipmentUpdateRequest;
import com.example.medcentral.feature.equipment.model.response.EquipmentResponse;
import com.example.medcentral.feature.equipment.repository.database.interfaces.IEquipmentRepository;
import com.example.medcentral.feature.equipment.repository.database.interfaces.IEquipmentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService implements IEquipmentService {

    private final IEquipmentRepository equipmentRepository;
    private final IEquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentService(
            IEquipmentRepository equipmentRepository,
            IEquipmentMapper equipmentMapper
    ) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public long createEquipment(EquipmentCreateRequest request) {
        try {
            Gson gson = new Gson();
//        var myrequestbody = gson.toJson(request);
            //Gson is a package to help deal with JSON -> Convert to and from json
            var equipment = gson.fromJson(gson.toJson(request), Equipment.class);

            return equipmentRepository.createEquipment(equipment);
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<EquipmentResponse> getEquipments(EquipmentQueryParams queryParams) {
        try{
            return equipmentRepository
                    .getEquipments(queryParams)
                    .stream()
                    .map(equipmentMapper::toResponse)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public EquipmentResponse getEquipmentById(String equipmentId) {
        Equipment equipment = equipmentRepository.getEquipmentById(equipmentId);
        if (equipment == null) {
            throw new ResourceNotFoundException("Equipment not found of id: " + equipmentId);
        }
        return equipmentMapper.toResponse(equipment);
    }

    @Override
    public long updateEquipmentById(EquipmentUpdateRequest request) {
        try {

            // Optionally, verify student exists before updating
            // For example, you can have getStudentById and throw ResourceNotFoundException if not found

            Gson gson = new Gson();
            // Convert request to entity (you can map manually or use Gson)
            Equipment equipment = gson.fromJson(gson.toJson(request), Equipment.class);

            long affectedRows = equipmentRepository.updateEquipmentById(equipment);

            if (affectedRows == 0) {
//                throw new Exception("Equipment not found");
                throw new ResourceNotFoundException("Equipment with ID " + request.getEquipmentId() + " not found");
            }

            return affectedRows;
        }
        catch (Exception e) {
//            throw e;
            throw new RuntimeException(e);
        }


    }


    @Override
    public long deleteEquipmentById(String equipmentId) {
        long affectedRows = equipmentRepository.deleteEquipmentById(equipmentId);
        if (affectedRows == 0) {
            throw new ResourceNotFoundException("Student with ID " + equipmentId + " not found.");
        }

        return  affectedRows;
    }
}
