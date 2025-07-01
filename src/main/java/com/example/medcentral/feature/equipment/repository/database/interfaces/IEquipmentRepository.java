package com.example.medcentral.feature.equipment.repository.database.interfaces;



import com.example.medcentral.feature.equipment.model.entity.Equipment;
import com.example.medcentral.feature.equipment.model.request.EquipmentQueryParams;

import java.util.List;

public interface IEquipmentRepository {
    long createEquipment(Equipment equipment);

    List<Equipment> getEquipments(EquipmentQueryParams queryParams);

    Equipment getEquipmentById(String equipmentId);

    long updateEquipmentById(Equipment equipment);

    long deleteEquipmentById(String equipmentId);
}
