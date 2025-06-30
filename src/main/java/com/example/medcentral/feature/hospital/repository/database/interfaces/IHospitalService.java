package com.example.medcentral.feature.hospital.repository.database.interfaces;

import com.example.medcentral.feature.hospital.model.request.HospitalCreateRequest;
import com.example.medcentral.feature.hospital.model.request.HospitalQueryParams;
import com.example.medcentral.feature.hospital.model.request.HospitalUpdateRequest;
import com.example.medcentral.feature.hospital.model.response.HospitalResponse;
import com.example.medcentral.model.request.student.StudentUpdateRequest;

import java.util.List;

public interface IHospitalService {
    //createHospital
    long createHospital(HospitalCreateRequest request);
    //should take in request or so as we do in TS. Modify later

    //  getHospitals
    List<HospitalResponse> getHospitals(HospitalQueryParams queryParams);

    //getHospitalById
    HospitalResponse getHospitalById(String hospitalId);

    long updateHospitalById(HospitalUpdateRequest request);

    long deleteHospitalById(String hospitalId);
}


