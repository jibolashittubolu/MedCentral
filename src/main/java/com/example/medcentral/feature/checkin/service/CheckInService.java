package com.example.medcentral.feature.checkin.service;

import com.example.medcentral.feature.checkin.mapper.CheckInRequestMapper;
import com.example.medcentral.feature.checkin.model.entity.CheckIn;
import com.example.medcentral.feature.checkin.model.request.CreateCheckInRequest;
import com.example.medcentral.feature.checkin.model.response.CheckInResponse;
//import com.example.medcentral.feature.checkin.model.response.PatientResponse;
import com.example.medcentral.feature.checkin.repository.database.interfaces.CheckInRepository;
//import com.example.medcentral.feature.checkin.repository.database.interfaces.PatientRepository;
import com.example.medcentral.feature.checkin.util.CheckInValidator;
import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import com.example.medcentral.feature.patient.model.response.PatientResponse;
import com.example.medcentral.feature.patient.repository.database.interfaces.PatientRepository;
import com.example.medcentral.feature.patient.util.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final CheckInRequestMapper checkInRequestMapper;
//    private StaffRepository staffRepository;
//    private AppointmentRepository appointmentRepository;
    private final IHospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public CheckInService(CheckInRepository checkInRepository, CheckInRequestMapper checkInRequestMapper, IHospitalRepository hospitalRepository, PatientRepository patientRepository) {
        this.checkInRepository = checkInRepository;
        this.checkInRequestMapper = checkInRequestMapper;
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
    }

    public void createCheckin(CreateCheckInRequest request, UUID staffId) {
        if (request == null) {
            throw new IllegalArgumentException("Check-in request must not be null.");
        }

        if (staffId == null) {
            throw new IllegalArgumentException("Staff ID must not be null.");
        }

//        Staff staff = staffRepository.getStaffById(staffId);

//        EntityValidator.validateStaff(staff);

        CheckIn checkIn = checkInRequestMapper.toEntity(request);
        checkInRepository.createCheckIn(checkIn, staffId);
    }

    public void createCheckInFromAppointment(UUID appointmentId, UUID staffId) {
        if (appointmentId == null || staffId == null) {
            throw new IllegalArgumentException("Appointment ID and Staff ID must not be null.");
        }

//        Staff staff = staffRepository.getStaffById(staffId);
//        Appointment appointment = appointmentRepository.getAppointmentById(appointmentId);

//        EntityValidator.validateStaff(staff);
//        EntityValidator.validateAppointment(appointment);

        checkInRepository.createCheckInFromAppointment(appointmentId, staffId);
    }

    public void updateCheckInStatusAndDiagnosis(String checkInDiagnosis, UUID checkInId, UUID staffId, String checkInStatus, UUID hospitalId) {
        if (checkInId == null || staffId == null || hospitalId == null) {
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

//        Staff staff = staffRepository.getStaffById(staffId);
//        EntityValidator.validateStaff(staff);


        // Optionally validate checkInStatus
        if (checkInStatus != null && !checkInStatus.isBlank()) {
            List<String> validStatuses = List.of("ACTIVE", "COMPLETED", "CANCELLED", "ABANDONED");
            if (!validStatuses.contains(checkInStatus.toUpperCase())) {
                throw new IllegalArgumentException("Invalid check-in status: " + checkInStatus);
            }
        }

        checkInRepository.updateCheckInStatusAndDiagnosis(checkInDiagnosis, staffId, checkInId, checkInStatus, hospitalId);
    }

    public void addCheckInPatientNotes(UUID checkInId, UUID hospitalId, String patientNotes, UUID staffId) {
        if (checkInId == null || staffId == null || hospitalId == null) {
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
//        EntityValidator.validateStaff(staff);

        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);
        CheckInValidator.validate(checkIn);

        checkInRepository.addCheckInPatientNotes(checkInId, hospitalId, patientNotes, staffId);
    }

    public CheckInResponse getCheckInById(UUID checkInId, UUID hospitalId) {
        if (checkInId == null || hospitalId == null) {
            throw new IllegalArgumentException("Check-in ID and hospital ID must be provided.");
        }
        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        CheckInResponse checkInResponse = checkInRepository.getCheckInById(checkInId, hospitalId);
        if (checkInResponse == null) {throw new IllegalArgumentException("Check-in with ID " + checkInId + " does not exist.");}
        return checkInResponse;
    }

    public List<CheckInResponse> getAllCheckIns(UUID hospitalId) {
        if (hospitalId == null) {throw new IllegalArgumentException("Hospital ID must not be null.");}

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        List<CheckInResponse> checkInResponseList = checkInRepository.getAllCheckIns(hospitalId);
        if (checkInResponseList == null) {throw new IllegalArgumentException("No check-ins found at hospital with ID " + hospitalId);}
        return checkInResponseList;
    }

    public List<CheckInResponse> getAllCheckInsByPatientId(UUID hospitalId, UUID patientId) {
        if (hospitalId == null || patientId == null) {throw new IllegalArgumentException("Hospital ID and Patient ID must not be null.");}

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        PatientResponse patientResponse = patientRepository.getPatientById(patientId);
        PatientValidator.validate(patientResponse);

        List<CheckInResponse> checkInResponses = checkInRepository.getAllCheckInsByPatientId(hospitalId, patientId);
        if (checkInResponses == null) {throw new IllegalArgumentException("No check-ins found for patient with ID " + patientId);}
        return checkInResponses;
    }

    public List<CheckInResponse> getActiveCheckInsByPatientId(UUID hospitalId, UUID patientId) {
        if (hospitalId == null || patientId == null) {throw new IllegalArgumentException("Hospital ID and Patient ID must not be null.");}

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        PatientResponse patientResponse = patientRepository.getPatientById(patientId);
        PatientValidator.validate(patientResponse);

        List<CheckInResponse> checkInResponses = checkInRepository.getAllCheckInsByPatientId(hospitalId, patientId);
        if (checkInResponses == null) {throw new IllegalArgumentException("No ACTIVE check-ins found for Patient with ID " + patientId);}
        return checkInResponses;
    }

    public List<CheckInResponse> listAllCheckInStaffParticipatedIn(UUID hospitalId, UUID staffId) {
        if (staffId == null || hospitalId == null) {
            throw new IllegalArgumentException("Staff ID and hospital ID must be provided.");
        }

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

//        Staff staff = staffRepository.getStaffById(staffId);
//        EntityValidator.validateStaff(staff);

        List<CheckInResponse> checkInResponses = checkInRepository.listAllCheckInStaffParticipatedIn(hospitalId, staffId);
        if (checkInResponses == null) {throw new IllegalArgumentException("No check-ins found for staff with ID " + staffId);}
        return checkInResponses;
    }
}
