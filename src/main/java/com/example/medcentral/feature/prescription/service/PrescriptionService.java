package com.example.medcentral.feature.prescription.service;

import com.example.medcentral.exception.ResourceNotFoundException;


import com.example.medcentral.feature.prescription.mapper.IPrescriptionMapper;
import com.example.medcentral.feature.prescription.model.entity.Prescription;
import com.example.medcentral.feature.prescription.model.request.PrescriptionCreateRequest;
import com.example.medcentral.feature.prescription.model.request.PrescriptionQueryParams;
import com.example.medcentral.feature.prescription.model.request.PrescriptionUpdateRequest;
import com.example.medcentral.feature.prescription.model.response.PrescriptionResponse;
import com.example.medcentral.feature.prescription.repository.database.interfaces.IPrescriptionRepository;
import com.example.medcentral.feature.prescription.repository.database.interfaces.IPrescriptionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService implements IPrescriptionService {

    private final IPrescriptionRepository prescriptionRepository;
    private final IPrescriptionMapper prescriptionMapper;

    @Autowired
    public PrescriptionService(
            IPrescriptionRepository prescriptionRepository,
            IPrescriptionMapper prescriptionMapper
    ) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public long createPrescription(PrescriptionCreateRequest request) {
        try {
            Gson gson = new Gson();
//        var myrequestbody = gson.toJson(request);
            //Gson is a package to help deal with JSON -> Convert to and from json
            var prescription = gson.fromJson(gson.toJson(request), Prescription.class);

            return prescriptionRepository.createPrescription(prescription);
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<PrescriptionResponse> getPrescriptions(PrescriptionQueryParams queryParams) {
        try{
            return prescriptionRepository
                    .getPrescriptions(queryParams)
                    .stream()
                    .map(prescriptionMapper::toResponse)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public PrescriptionResponse getPrescriptionById(String prescriptionId) {
        Prescription prescription = prescriptionRepository.getPrescriptionById(prescriptionId);
        if (prescription == null) {
            throw new ResourceNotFoundException("Prescription not found of id: " + prescriptionId);
        }
        return prescriptionMapper.toResponse(prescription);
    }

    @Override
    public long updatePrescriptionById(PrescriptionUpdateRequest request) {
        try {

            // Optionally, verify student exists before updating
            // For example, you can have getStudentById and throw ResourceNotFoundException if not found

            Gson gson = new Gson();
            // Convert request to entity (you can map manually or use Gson)
            Prescription prescription = gson.fromJson(gson.toJson(request), Prescription.class);

            long affectedRows = prescriptionRepository.updatePrescriptionById(prescription);

            if (affectedRows == 0) {
//                throw new Exception("Prescription not found");
                throw new ResourceNotFoundException("Prescription with ID " + request.getPrescriptionId() + " not found");
            }

            return affectedRows;
        }
        catch (Exception e) {
//            throw e;
            throw new RuntimeException(e);
        }


    }


    @Override
    public long deletePrescriptionById(String prescriptionId) {
        long affectedRows = prescriptionRepository.deletePrescriptionById(prescriptionId);
        if (affectedRows == 0) {
            throw new ResourceNotFoundException("Prescription with ID " + prescriptionId + " not found.");
        }

        return  affectedRows;
    }
}
