package com.example.medcentral.feature.sale.service;

import com.example.medcentral.feature.sale.mapper.SaleRequestMapper;
//import com.example.medcentral.feature.sale.model.response.CheckInResponse;
import com.example.medcentral.feature.sale.model.response.SaleResponse;
//import com.example.medcentral.feature.sale.repository.database.interfaces.CheckInRepository;
import com.example.medcentral.feature.sale.repository.database.interfaces.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SaleService {

    private SaleRepository saleRepository;
    private SaleRequestMapper saleRequestMapper;
//    private HospitalRepository hospitalRepository;
//    private StaffRepository staffRepository;
//    private CheckInRepository checkInRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleRequestMapper saleRequestMapper) {
        this.saleRepository = saleRepository;
        this.saleRequestMapper = saleRequestMapper;
//        this.checkInRepository = checkInRepository;
    }

    public void createSale(UUID checkInId, UUID staffId, UUID hospitalId) {
        if (checkInId == null || staffId == null) {
            throw new IllegalArgumentException("Check-in ID and Staff ID can not be null");
        }

//        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
//        Staff staff = staffRepository.getStaffById(staffId);
//        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);

//        EntityValidator.validateHospital(hospital);
//        EntityValidator.validateStaff(staff);
//        EntityValidator.validateCheckIn(checkIn);

        int sale = saleRepository.createSale(checkInId, staffId, hospitalId);
    }

    public SaleResponse getSaleById(UUID saleId, UUID hospitalId) {
        if (saleId == null || hospitalId == null) {
            throw new IllegalArgumentException("Sale ID and Hospital ID can not be null");
        }

//        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);

//        EntityValidator.validateHospital(hospital);

        SaleResponse sale = saleRepository.getSaleById(saleId, hospitalId);
        if (sale == null) {
            throw new IllegalArgumentException("No sale with id " + saleId + " found at hospital " + hospitalId);
        }

        return sale;
    }

    public List<SaleResponse> listAllSaleByCheckIn(UUID checkInId, UUID hospitalId) {
        if (checkInId == null || hospitalId == null) {
            throw new IllegalArgumentException("Check-in ID and Hospital ID can not be null");
        }

//        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
//        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);

//        EntityValidator.validateHospital(hospital);
//        EntityValidator.validateCheckIn(checkIn);

        List<SaleResponse> saleResponses = saleRepository.listAllSaleByCheckIn(checkInId, hospitalId);
        if (saleResponses == null) {
            throw new IllegalArgumentException("No sales found for check-in " + checkInId + " at hospital " + hospitalId);
        }
        return saleResponses;
    }
}
