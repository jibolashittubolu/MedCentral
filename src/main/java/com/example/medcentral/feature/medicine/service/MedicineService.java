package com.example.medcentral.feature.medicine.service;

import com.example.medcentral.feature.medicine.mapper.MedicineRequestMapper;
import com.example.medcentral.feature.medicine.model.entity.Medicine;
import com.example.medcentral.feature.medicine.model.request.CreateMedicineRequest;
import com.example.medcentral.feature.medicine.model.request.UpdateMedicineRequest;
import com.example.medcentral.feature.medicine.model.response.MedicineResponse;
import com.example.medcentral.feature.medicine.repository.database.interfaces.MedicineRepository;
import com.example.medcentral.feature.medicine.util.validator.MedicineValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicineService {

    private MedicineRepository medicineRepository;
    private MedicineRequestMapper medicineRequestMapper;
//    private HospitalRepository hospitalRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository, MedicineRequestMapper medicineRequestMapper) {
        this.medicineRepository = medicineRepository;
        this.medicineRequestMapper = medicineRequestMapper;
    }

    public void createMedicine(CreateMedicineRequest request) {
        if (request == null) throw new IllegalArgumentException("Medicine request cannot be null");

//        Hospital hospital = hospitalRepository.getHospitalById(request.getMedicineHospitalId());

//        EntityValidator.validateHospital(hospital);

        Medicine medicine = medicineRequestMapper.toEntity(request);
        medicineRepository.createMedicine(medicine);
    }

    public void updateMedicine(UpdateMedicineRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Medicine update request cannot be null.");
        }

        UUID hospitalId = request.getMedicineHospitalId();
        UUID medicineId = request.getMedicineId();

//        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);

//        EntityValidator.validateHospital(hospital);

        // Check if medicine exists
        MedicineResponse medicineResponse = medicineRepository.getMedicineById(medicineId, hospitalId);

        MedicineValidator.validate(medicineResponse);

        // Map and update
        Medicine medicine = medicineRequestMapper.toEntity(request);
        medicineRepository.updateMedicine(medicine);
    }


    public void updateMedicineQuantity(int medicineQuantity, UUID hospitalId, UUID medicineId) {
        if (medicineQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        if (hospitalId == null || medicineId == null) {
            throw new IllegalArgumentException("Hospital ID and Medicine ID must be provided.");
        }

//        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        MedicineResponse medicine = medicineRepository.getMedicineById(medicineId, hospitalId);

//        EntityValidator.validateHospital(hospital);
        MedicineValidator.validate(medicine);

        medicineRepository.updateMedicineQuantity(medicineQuantity, hospitalId, medicineId);
    }


    public MedicineResponse getMedicineById(UUID medicineId, UUID hospitalId) {
        if (medicineId == null || hospitalId == null) {
            throw new IllegalArgumentException("Medicine ID and hospital ID must be provided.");
        }

        MedicineResponse medicine = medicineRepository.getMedicineById(medicineId, hospitalId);

        MedicineValidator.validate(medicine);

        return medicine;
    }

    public List<MedicineResponse> getMedicineByHospitalId(UUID hospitalId) {
        if (hospitalId == null) {
            throw new IllegalArgumentException("Hospital ID must be provided.");
        }

        List<MedicineResponse> medicines = medicineRepository.getMedicineByHospitalId(hospitalId);
        if (medicines == null || medicines.isEmpty()) {
            throw new IllegalArgumentException("No medicines found for the given hospital.");
        }

        return medicines;
    }

    public List<MedicineResponse> getMedicineWithLowStock(UUID hospitalId) {
        if (hospitalId == null) {
            throw new IllegalArgumentException("Hospital ID must be provided.");
        }

        List<MedicineResponse> medicines = medicineRepository.getMedicineWithLowStock(hospitalId);
        if (medicines == null || medicines.isEmpty()) {
            throw new IllegalArgumentException("No medicines found for the given hospital.");
        }
        return medicines;
    }

    public List<MedicineResponse> getMedicineByName(String medicineName, UUID hospitalId) {
        if (hospitalId == null) {
            throw new IllegalArgumentException("Hospital ID must be provided.");
        }

        if (medicineName == null || medicineName.trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine name must be provided.");
        }

        List<MedicineResponse> medicines = medicineRepository.getMedicineByName(medicineName, hospitalId);

        if (medicines == null || medicines.isEmpty()) {
            throw new IllegalArgumentException("No medicines named '" + medicineName + "' found for the given hospital.");
        }

        return medicines;
    }

    public void deleteMedicineById(UUID medicineId, UUID hospitalId) {
        if (medicineId == null || hospitalId == null) {
            throw new IllegalArgumentException("Medicine ID and hospital ID must be provided.");
        }

        MedicineResponse medicine = medicineRepository.getMedicineById(medicineId, hospitalId);

        MedicineValidator.validate(medicine);

        medicineRepository.deleteMedicineById(medicineId, hospitalId);
    }
}
