package com.example.medcentral.feature.appointment.service;

import com.example.medcentral.feature.appointment.mapper.AppointmentRequestMapper;
import com.example.medcentral.feature.appointment.model.entity.Appointment;
import com.example.medcentral.feature.appointment.model.request.CreateAppointmentRequest;
import com.example.medcentral.feature.appointment.model.request.UpdateAppointmentRequest;
import com.example.medcentral.feature.appointment.model.response.AppointmentResponse;
import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalService;
import com.example.medcentral.feature.patient.model.response.PatientResponse;
import com.example.medcentral.feature.patient.repository.database.interfaces.PatientRepository;
import com.example.medcentral.feature.patient.util.validator.PatientValidator;
import com.example.medcentral.feature.staff.model.response.StaffResponse;
import com.example.medcentral.feature.appointment.repository.database.interfaces.AppointmentRepository;
import com.example.medcentral.feature.staff.repository.database.interfaces.StaffRepository;
import com.example.medcentral.feature.appointment.util.validator.AppointmentValidator;
import com.example.medcentral.feature.staff.util.validator.StaffValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final IHospitalRepository hospitalRepository;
    private final AppointmentRequestMapper appointmentRequestMapper;

    @Autowired
    public AppointmentService(final AppointmentRepository appointmentRepository, StaffRepository staffRepository, AppointmentRequestMapper appointmentRequestMapper, PatientRepository patientRepository, IHospitalRepository hospitalRepository, IHospitalService hospitalService) {
        this.appointmentRepository = appointmentRepository;
        this.staffRepository = staffRepository;
        this.appointmentRequestMapper = appointmentRequestMapper;
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public void createAppointment(CreateAppointmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Appointment request must not be null");
        }
        PatientResponse patientResponse = patientRepository.getPatientById(request.getAppointmentPatientId());
        PatientValidator.validate(patientResponse);

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(request.getAppointmentHospitalId()));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        StaffResponse staffResponse = staffRepository.getStaffById(request.getAppointmentStaffId());
        StaffValidator.validate(staffResponse);

        Appointment appointment = appointmentRequestMapper.toEntity(request);
        appointmentRepository.createAppointment(appointment);
    }

    public void updateAppointment(UpdateAppointmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Appointment request must not be null");
        }
        if (request.getAppointmentStaffId() != null) {
            StaffResponse staffResponse = staffRepository.getStaffById(request.getAppointmentStaffId());
            StaffValidator.validate(staffResponse);
        }
        if (request.getAppointmentDateTime() != null &&
                request.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment datetime cannot be in the past");
        }
        AppointmentResponse appointmentResponse = appointmentRepository.getAppointmentById(request.getAppointmentId());
        AppointmentValidator.validate(appointmentResponse);

        Appointment appointment = appointmentRequestMapper.toEntity(request);
        appointmentRepository.updateAppointment(appointment);
    }

    public void rescheduleAppointment(UpdateAppointmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Appointment request must not be null");
        }
        if (request.getAppointmentDateTime() == null) {
            throw new IllegalArgumentException("Appointment datetime is required");
        }
        if (request.getAppointmentDateTime() != null &&
                request.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment datetime cannot be in the past");
        }
        AppointmentResponse appointmentResponse = appointmentRepository.getAppointmentById(request.getAppointmentId());
        AppointmentValidator.validate(appointmentResponse);


        Appointment appointment = appointmentRequestMapper.toEntity(request);
        appointmentRepository.rescheduleAppointment(appointment);
    }

    public void cancelAppointment(UUID appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment id must not be null");
        }

        AppointmentResponse appointmentResponse = appointmentRepository.getAppointmentById(appointmentId);
        AppointmentValidator.validate(appointmentResponse);

        appointmentRepository.cancelAppointment(appointmentId);
    }

    public void deleteAppointment(UUID appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment id must not be null");
        }

        AppointmentResponse appointmentResponse = appointmentRepository.getAppointmentById(appointmentId);
        AppointmentValidator.validate(appointmentResponse);

        appointmentRepository.deleteAppointment(appointmentId);
    }

    public AppointmentResponse getAppointmentById(UUID appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment id must not be null");
        }

        AppointmentResponse appointmentResponse = appointmentRepository.getAppointmentById(appointmentId);
        AppointmentValidator.validate(appointmentResponse);
        return appointmentResponse;
    }

    public List<AppointmentResponse> getAppointmentByPatientId(UUID appointmentPatientId) {
        if (appointmentPatientId == null) {
            throw new IllegalArgumentException("Appointment patient id must not be null");
        }

        PatientResponse patientResponse = patientRepository.getPatientById(appointmentPatientId);
        PatientValidator.validate(patientResponse);

        List<AppointmentResponse> appointmentResponses = appointmentRepository.getAppointmentByPatientId(appointmentPatientId);
        for (AppointmentResponse appointmentResponse : appointmentResponses) {
            AppointmentValidator.validate(appointmentResponse);
        }
        return appointmentResponses;
    }
}
