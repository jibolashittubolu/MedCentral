package com.example.v_medcentral.feature.appointment.service;

import com.example.v_medcentral.mapper.AppointmentRequestMapper;
import com.example.v_medcentral.model.entity.Appointment;
import com.example.v_medcentral.model.request.CreateAppointmentRequest;
import com.example.v_medcentral.model.request.UpdateAppointmentRequest;
import com.example.v_medcentral.model.response.AppointmentResponse;
import com.example.v_medcentral.model.response.StaffResponse;
import com.example.v_medcentral.repository.database.interfaces.AppointmentRepository;
import com.example.v_medcentral.repository.database.interfaces.StaffRepository;
import com.example.v_medcentral.util.validator.AppointmentValidator;
import com.example.v_medcentral.util.validator.StaffValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
//    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
//    private final HospitalRepository hospitalRepository;
    private final AppointmentRequestMapper appointmentRequestMapper;

    @Autowired
    public AppointmentService(final AppointmentRepository appointmentRepository, StaffRepository staffRepository, AppointmentRequestMapper appointmentRequestMapper) {
        this.appointmentRepository = appointmentRepository;
//        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
        this.appointmentRequestMapper = appointmentRequestMapper;
    }

    public void createAppointment(CreateAppointmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Appointment request must not be null");
        }
//        PatientResponse patientResponse = patientRepository.getPatientById(request.getAppointmentPatientId());
//        PatientValidator.validate(patientResponse);
//        Hospital hospital = hospitalRepository.getHospitalById(request.getAppointmentHospitalId());
//        HospitalValidator.validate(hospital);
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

//        PatientResponse patientResponse = patientRepository.getPatientById(appointmentPatientId);
//        PatientValidator.validate(patientResponse);

        List<AppointmentResponse> appointmentResponses = appointmentRepository.getAppointmentByPatientId(appointmentPatientId);
        for (AppointmentResponse appointmentResponse : appointmentResponses) {
            AppointmentValidator.validate(appointmentResponse);
        }
        return appointmentResponses;
    }
}
