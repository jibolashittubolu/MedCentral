package com.example.medcentral.feature.patient.service;

import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import com.example.medcentral.feature.patient.mapper.PatientRequestMapper;
import com.example.medcentral.feature.patient.model.entity.Patient;
import com.example.medcentral.feature.patient.model.request.CreatePatientRequest;
import com.example.medcentral.feature.patient.model.request.UpdatePatientRequest;
import com.example.medcentral.feature.patient.model.response.PatientResponse;
import com.example.medcentral.feature.patient.repository.database.interfaces.PatientRepository;
import com.example.medcentral.feature.patient.util.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientRequestMapper patientRequestMapper;
    private final IHospitalRepository hospitalRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientRequestMapper patientRequestMapper,  IHospitalRepository hospitalRepository) {
        this.patientRepository = patientRepository;
        this.patientRequestMapper = patientRequestMapper;
        this.hospitalRepository = hospitalRepository;
    }

    public void createPatient(CreatePatientRequest request) {
        if (request == null) throw new IllegalArgumentException("Patient request cannot be null");

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(request.getPatientRegisteredAt()));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        Patient patient = patientRequestMapper.toEntity(request);
        patientRepository.createPatient(patient);
    }

    public void updatePatient(UpdatePatientRequest request) {
        if (request == null) throw new IllegalArgumentException("Patient request cannot be null");

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(request.getPatientRegisteredAt()));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        PatientResponse patientResponse = patientRepository.getPatientById(request.getPatientId());
        PatientValidator.validate(patientResponse);

        Patient patient = patientRequestMapper.toEntity(request);
        patientRepository.updatePatient(patient);
    }

    public PatientResponse getPatientById(UUID patientId) {

        PatientResponse patientResponse = patientRepository.getPatientById(patientId);

        PatientValidator.validate(patientResponse);

        return patientRepository.getPatientById(patientId);
    }

    public List<PatientResponse> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public List<PatientResponse> getPatientBySearchQuery(String searchQuery) {
        return patientRepository.getPatientsBySearchQuery(searchQuery);
    }

    public void deletePatientById(UUID patientId) {

        PatientResponse patientResponse = patientRepository.getPatientById(patientId);

        PatientValidator.validate(patientResponse);

        patientRepository.deletePatientById(patientId);
    }
}
