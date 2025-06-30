package com.example.medcentral.feature.auditpatient.service;

import com.example.medcentral.feature.auditpatient.mapper.PatientRequestMapper;
import com.example.medcentral.feature.auditpatient.model.entity.Patient;
import com.example.medcentral.feature.auditpatient.model.request.CreatePatientRequest;
import com.example.medcentral.feature.auditpatient.model.request.UpdatePatientRequest;
import com.example.medcentral.feature.auditpatient.model.response.PatientResponse;
import com.example.medcentral.feature.auditpatient.repository.database.interfaces.PatientRepository;
import com.example.medcentral.feature.auditpatient.util.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PatientRequestMapper patientRequestMapper;
//    private HospitalRepository hospitalRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientRequestMapper patientRequestMapper) {
        this.patientRepository = patientRepository;
        this.patientRequestMapper = patientRequestMapper;
//        this.hospitalRepository = hospitalRepository;
    }

    public void createPatient(CreatePatientRequest request) {
        if (request == null) throw new IllegalArgumentException("Patient request cannot be null");

//        Hospital hospital = hospitalRepository.getHospitalById(request.getPatientRegisteredAt());

//        EntityValidator.validateHospital(hospital);

        Patient patient = patientRequestMapper.toEntity(request);
        patientRepository.createPatient(patient);
    }

    public void updatePatient(UpdatePatientRequest request) {
        if (request == null) throw new IllegalArgumentException("Patient request cannot be null");

//        Hospital hospital = hospitalRepository.getHospitalById(request.getPatientRegisteredAt());
//
//        EntityValidator.validateHospital(hospital);
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
