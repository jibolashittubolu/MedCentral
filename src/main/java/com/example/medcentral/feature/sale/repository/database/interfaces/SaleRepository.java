package com.example.medcentral.feature.sale.repository.database.interfaces;

import com.example.medcentral.feature.sale.model.response.SaleResponse;

import java.util.List;
import java.util.UUID;

public interface SaleRepository {

    int createSale(UUID checkInId, UUID staffId, UUID hospitalId);
    SaleResponse getSaleById(UUID saleId, UUID hospitalId);
    List<SaleResponse> listAllSaleByCheckIn(UUID checkInId,  UUID hospitalId);
//    List<Prescription> listAllPrescriptionsInSale(UUID saleId, UUID hospitalId);
}