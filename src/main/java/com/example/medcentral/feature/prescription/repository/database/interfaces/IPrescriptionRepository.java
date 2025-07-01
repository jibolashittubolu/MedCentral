package com.example.medcentral.feature.prescription.repository.database.interfaces;

import com.example.medcentral.feature.prescription.model.entity.Prescription;
import com.example.medcentral.feature.prescription.model.request.PrescriptionQueryParams;

import java.util.List;

public interface IPrescriptionRepository {
    long createPrescription(Prescription prescription);

    List<Prescription> getPrescriptions(PrescriptionQueryParams queryParams);

    Prescription getPrescriptionById(String prescriptionId);

    long updatePrescriptionById(Prescription prescription);

    long deletePrescriptionById(String prescriptionId);
}
