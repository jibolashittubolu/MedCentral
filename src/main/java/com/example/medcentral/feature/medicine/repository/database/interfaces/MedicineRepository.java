package com.example.medcentral.feature.medicine.repository.database.interfaces;

import com.example.medcentral.feature.medicine.model.entity.Medicine;
import com.example.medcentral.feature.medicine.model.response.MedicineResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineRepository {

    int createMedicine(Medicine medicine);
    int updateMedicine(Medicine medicine);
    int updateMedicineQuantity(int medicineQuantity, UUID hospitalId, UUID medicineId);
    MedicineResponse getMedicineById(UUID medicineId, UUID hospitalId);
    List<MedicineResponse> getMedicineByHospitalId(UUID hospitalId);
    List<MedicineResponse> getMedicineWithLowStock(UUID hospitalId);
    List<MedicineResponse> getMedicineByName(String medicineName, UUID hospitalId);
    int deleteMedicineById(UUID medicineId, UUID hospitalId);
}
