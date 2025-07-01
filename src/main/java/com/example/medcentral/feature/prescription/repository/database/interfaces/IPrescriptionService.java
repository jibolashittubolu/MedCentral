package com.example.medcentral.feature.prescription.repository.database.interfaces;


import com.example.medcentral.feature.prescription.model.request.PrescriptionCreateRequest;
import com.example.medcentral.feature.prescription.model.request.PrescriptionQueryParams;
import com.example.medcentral.feature.prescription.model.request.PrescriptionUpdateRequest;
import com.example.medcentral.feature.prescription.model.response.PrescriptionResponse;

import java.util.List;

public interface IPrescriptionService {
    //createPrescription
    long createPrescription(PrescriptionCreateRequest request);
    //should take in request or so as we do in TS. Modify later

    //  getPrescriptions
    List<PrescriptionResponse> getPrescriptions(PrescriptionQueryParams queryParams);

    //getPrescriptionById
    PrescriptionResponse getPrescriptionById(String prescriptionId);

    long updatePrescriptionById(PrescriptionUpdateRequest request);

    long deletePrescriptionById(String prescriptionId);
}


