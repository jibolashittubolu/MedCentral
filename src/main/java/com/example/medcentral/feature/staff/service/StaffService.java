package com.example.medcentral.feature.staff.service;

import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import com.example.medcentral.feature.staff.mapper.StaffRequestMapper;
import com.example.medcentral.feature.staff.model.entity.Staff;
import com.example.medcentral.feature.staff.model.request.CreateStaffRequest;
import com.example.medcentral.feature.staff.model.request.UpdateStaffRequest;
import com.example.medcentral.feature.staff.model.response.StaffResponse;
import com.example.medcentral.feature.staff.repository.database.interfaces.StaffRepository;
import com.example.medcentral.feature.staff.util.validator.StaffValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final StaffRequestMapper staffRequestMapper;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository, StaffRequestMapper staffRequestMapper,  IHospitalRepository hospitalRepository) {
        this.staffRepository = staffRepository;
        this.staffRequestMapper = staffRequestMapper;
        this.hospitalRepository = hospitalRepository;
    }

    public void createStaff(CreateStaffRequest request) {
        if (request == null) {throw new IllegalArgumentException("Staff request must not be null");}

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(request.getStaffHospitalId()));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        Staff staff = staffRequestMapper.toEntity(request);
        staffRepository.createStaff(staff);
    }

    public void updateStaff(UpdateStaffRequest request) {
        if (request == null) {throw new IllegalArgumentException("Staff request must not be null");}

        if (request.getStaffHospitalId() != null) {
            Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(request.getStaffHospitalId()));
            if (hospital == null) {
                throw new IllegalArgumentException("Hospital not found.");
            }
            if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
                throw new IllegalArgumentException("Hospital is not ACTIVE.");
            }
        }

        Staff staff = staffRequestMapper.toEntity(request);
        staffRepository.updateStaff(staff);
    }

    public StaffResponse getStaffById(UUID staffId) {
        if (staffId == null) {throw new IllegalArgumentException("Staff ID must not be null");}

        StaffResponse staffResponse = staffRepository.getStaffById(staffId);
        StaffValidator.validate(staffResponse);

        return staffResponse;
    }

    public List<StaffResponse> getStaffByHospital(UUID hospitalId) {
        if (hospitalId == null) {throw new IllegalArgumentException("Hospital ID must not be null");}

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        List<StaffResponse> staff = staffRepository.getStaffByHosital(hospitalId);
        if (staff == null) {throw new IllegalArgumentException("No staff found for hospital id" + hospitalId);}
        return staff;
    }

    public void deleteStaff(UUID staffId) {
        if (staffId == null) {throw new IllegalArgumentException("Staff ID must not be null");}

        StaffResponse staffResponse = staffRepository.getStaffById(staffId);
        StaffValidator.validate(staffResponse);

        staffRepository.deleteStaff(staffId);
    }
}
