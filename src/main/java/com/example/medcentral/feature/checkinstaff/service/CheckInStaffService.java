package com.example.medcentral.feature.checkinstaff.service;

import com.example.medcentral.feature.checkin.model.response.CheckInResponse;
import com.example.medcentral.feature.checkin.repository.database.interfaces.CheckInRepository;
import com.example.medcentral.feature.checkin.util.CheckInValidator;
import com.example.medcentral.feature.checkinstaff.mapper.CheckInStaffRequestMapper;
//import com.example.medcentral.feature.checkinstaff.model.response.CheckInResponse;
import com.example.medcentral.feature.checkinstaff.model.response.CheckInStaffResponse;
//import com.example.medcentral.feature.checkinstaff.repository.database.interfaces.CheckInRepository;
import com.example.medcentral.feature.checkinstaff.repository.database.interfaces.CheckInStaffRepository;
import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CheckInStaffService {
    private CheckInStaffRepository checkInStaffRepository;
    private CheckInRepository checkInRepository;
    private CheckInStaffRequestMapper checkInStaffRequestMapper;
//    private StaffRepository staffRepository;
//    private AppointmentRepository appointmentRepository;
    private IHospitalRepository hospitalRepository;


    @Autowired
    public CheckInStaffService(CheckInStaffRepository checkInStaffRepository, CheckInStaffRequestMapper checkInStaffRequestMapper, CheckInRepository checkInRepository, IHospitalRepository hospitalRepository) {
        this.checkInStaffRepository = checkInStaffRepository;
        this.checkInStaffRequestMapper = checkInStaffRequestMapper;
        this.checkInRepository = checkInRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public void assignCheckInStaff(UUID checkInId, UUID hospitalId, UUID staffId){
        if (checkInId == null || hospitalId == null || staffId == null) {
            throw new IllegalArgumentException("Check-in ID, staff ID and hospital ID must be provided.");
        }
        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

//        Staff staff = staffRepository.getStaffById(staffId);
        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);
        CheckInValidator.validate(checkIn);

//        EntityValidator.validateStaff(staff);

        CheckInResponse checkInResponse = checkInRepository.getCheckInById(checkInId, hospitalId);
        if (checkInResponse == null) {throw new IllegalArgumentException("Check-in with ID " + checkInId + " does not exist.");}

        checkInStaffRepository.assignStaffToCheckIn(checkInId, staffId);
    }

    public void assignMultipleStaffToCheckIn(UUID checkInId, UUID hospitalId, List<UUID> staffIds) {
        if (checkInId == null || hospitalId == null || staffIds == null) {
            throw new IllegalArgumentException("Check-in ID, staff ID and hospital ID must be provided.");
        }

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);
        CheckInValidator.validate(checkIn);

        for (UUID staffId : staffIds) {
//            Staff staff = staffRepository.getStaffById(staffId);
//            EntityValidator.validateStaff(staff);
        }

        checkInStaffRepository.assignMultipleStaffToCheckIn(checkInId, hospitalId, staffIds);
    }

    public List<CheckInStaffResponse> listCheckInStaff(UUID checkInId, UUID hospitalId) {
        if (checkInId == null || hospitalId == null) {
            throw  new IllegalArgumentException("Check-in ID and hospital ID must be provided.");
        }
        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);
        CheckInValidator.validate(checkIn);

        List<CheckInStaffResponse> checkInStaffResponses = checkInStaffRepository.listAllCheckInStaff(checkInId, hospitalId);
        if  (checkInStaffResponses == null) {throw new IllegalArgumentException("No staff assigned to check-in with ID " + checkInId + ".");}
        return checkInStaffResponses;
    }
}
