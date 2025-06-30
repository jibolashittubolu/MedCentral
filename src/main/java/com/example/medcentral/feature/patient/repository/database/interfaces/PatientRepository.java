package com.example.medcentral.feature.patient.repository.database.interfaces;

import com.example.medcentral.feature.patient.model.entity.Patient;
import com.example.medcentral.feature.patient.model.response.PatientResponse;

import java.util.List;
import java.util.UUID;

public interface PatientRepository {

    int createPatient(Patient patient);
    int updatePatient(Patient patient);
    PatientResponse getPatientById(UUID patientId);
    List<PatientResponse> getAllPatients();
    List<PatientResponse> getPatientsBySearchQuery(String searchQuery);
    int deletePatientById(UUID patientId);
//    List<CheckIn> getCheckInsByPatientId(UUID patientId);
}
