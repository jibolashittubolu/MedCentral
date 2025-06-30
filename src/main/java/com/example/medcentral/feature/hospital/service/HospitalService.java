package com.example.medcentral.feature.hospital.service;

import com.example.medcentral.exception.ResourceNotFoundException;

import com.example.medcentral.feature.hospital.mapper.IHospitalMapper;
import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.model.request.HospitalCreateRequest;
import com.example.medcentral.feature.hospital.model.request.HospitalQueryParams;
import com.example.medcentral.feature.hospital.model.request.HospitalUpdateRequest;
import com.example.medcentral.feature.hospital.model.response.HospitalResponse;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalService;
import com.example.medcentral.model.entity.Student;
import com.example.medcentral.model.request.student.StudentUpdateRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService implements IHospitalService {

    private final IHospitalRepository hospitalRepository;
    private final IHospitalMapper hospitalMapper;

    @Autowired
    public HospitalService(
            IHospitalRepository hospitalRepository,
            IHospitalMapper hospitalMapper
    ) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
    }

    @Override
    public long createHospital(HospitalCreateRequest request) {
        try {
            Gson gson = new Gson();
//        var myrequestbody = gson.toJson(request);
            //Gson is a package to help deal with JSON -> Convert to and from json
            var hospital = gson.fromJson(gson.toJson(request), Hospital.class);

            return hospitalRepository.createHospital(hospital);
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<HospitalResponse> getHospitals(HospitalQueryParams queryParams) {
        try{
            return hospitalRepository
                    .getHospitals(queryParams)
                    .stream()
                    .map(hospitalMapper::toResponse)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public HospitalResponse getHospitalById(String hospitalId) {
        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        if (hospital == null) {
            throw new ResourceNotFoundException("Hospital not found of id: " + hospitalId);
        }
        return hospitalMapper.toResponse(hospital);
    }

    @Override
    public long updateHospitalById(HospitalUpdateRequest request) {
        try {

            // Optionally, verify student exists before updating
            // For example, you can have getStudentById and throw ResourceNotFoundException if not found

            Gson gson = new Gson();
            // Convert request to entity (you can map manually or use Gson)
            Hospital hospital = gson.fromJson(gson.toJson(request), Hospital.class);

            long affectedRows = hospitalRepository.updateHospitalById(hospital);

            if (affectedRows == 0) {
//                throw new Exception("Hospital not found");
                throw new ResourceNotFoundException("Hospital with ID " + request.getHospitalId() + " not found");
            }

            return affectedRows;
        }
        catch (Exception e) {
//            throw e;
            throw new RuntimeException(e);
        }


    }


    @Override
    public long deleteHospitalById(String hospitalId) {
        long affectedRows = hospitalRepository.deleteHospitalById(hospitalId);
        if (affectedRows == 0) {
            throw new ResourceNotFoundException("Student with ID " + hospitalId + " not found.");
        }

        return  affectedRows;
    }
}
