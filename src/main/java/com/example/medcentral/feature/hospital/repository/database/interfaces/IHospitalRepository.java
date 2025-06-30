package com.example.medcentral.feature.hospital.repository.database.interfaces;


import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.model.request.HospitalQueryParams;
import com.example.medcentral.model.entity.Student;

import java.util.List;

public interface IHospitalRepository {
    long createHospital(Hospital hospital);

    List<Hospital> getHospitals(HospitalQueryParams queryParams);

    Hospital getHospitalById(String hospitalId);

    long updateHospitalById(Hospital hospital);

    long deleteHospitalById(String hospitalId);
}
