package com.example.v_medcentral.feature.staff.service;

import com.example.v_medcentral.mapper.StaffRequestMapper;
import com.example.v_medcentral.model.entity.Staff;
import com.example.v_medcentral.model.request.CreateStaffRequest;
import com.example.v_medcentral.model.request.UpdateStaffRequest;
import com.example.v_medcentral.model.response.StaffResponse;
import com.example.v_medcentral.repository.database.interfaces.StaffRepository;
import com.example.v_medcentral.util.validator.StaffValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffService {

    private StaffRepository staffRepository;
    private StaffRequestMapper staffRequestMapper;
//    private HospitalRepository hospitalRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository, StaffRequestMapper staffRequestMapper) {
        this.staffRepository = staffRepository;
        this.staffRequestMapper = staffRequestMapper;
    }

    public void createStaff(CreateStaffRequest request) {
        if (request == null) {throw new IllegalArgumentException("Staff request must not be null");}

//        Hospital hospital = hospitalRepository.getHospitalById(request.getStaffHospitalId());
//        EntityValidator.validateHospital(hospital);

        Staff staff = staffRequestMapper.toEntity(request);
        staffRepository.createStaff(staff);
    }

    public void updateStaff(UpdateStaffRequest request) {
        if (request == null) {throw new IllegalArgumentException("Staff request must not be null");}

//        if (request.getStaffHospitalId() != null) {
//            Hospital hospital = hospitalRepository.getHospitalById(request.getStaffHospitalId());
//            EntityValidator.validateHospital(hospital);
//        }

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

//        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
//        EntityValidator.validateHospital(hospital);

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
