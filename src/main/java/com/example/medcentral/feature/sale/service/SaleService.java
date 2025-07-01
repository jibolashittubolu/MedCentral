package com.example.medcentral.feature.sale.service;

import com.example.medcentral.feature.checkin.model.response.CheckInResponse;
import com.example.medcentral.feature.checkin.repository.database.interfaces.CheckInRepository;
import com.example.medcentral.feature.checkin.util.CheckInValidator;
import com.example.medcentral.feature.hospital.model.entity.Hospital;
import com.example.medcentral.feature.hospital.repository.database.interfaces.IHospitalRepository;
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

    private final SaleRepository saleRepository;
    private final SaleRequestMapper saleRequestMapper;
    private final IHospitalRepository hospitalRepository;
//    private StaffRepository staffRepository;
    private final CheckInRepository checkInRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleRequestMapper saleRequestMapper, IHospitalRepository hospitalRepository, CheckInRepository checkInRepository) {
        this.saleRepository = saleRepository;
        this.saleRequestMapper = saleRequestMapper;
        this.hospitalRepository = hospitalRepository;
        this.checkInRepository = checkInRepository;
    }

    public void createSale(UUID checkInId, UUID staffId, UUID hospitalId) {
        if (checkInId == null || staffId == null) {
            throw new IllegalArgumentException("Check-in ID and Staff ID can not be null");
        }

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

//        Staff staff = staffRepository.getStaffById(staffId);
//        EntityValidator.validateStaff(staff);

        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);
        CheckInValidator.validate(checkIn);

        saleRepository.createSale(checkInId, staffId, hospitalId);
    }

    public SaleResponse getSaleById(UUID saleId, UUID hospitalId) {
        if (saleId == null || hospitalId == null) {
            throw new IllegalArgumentException("Sale ID and Hospital ID can not be null");
        }

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

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

        Hospital hospital = hospitalRepository.getHospitalById(String.valueOf(hospitalId));
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital not found.");
        }
        if (!"ACTIVE".equalsIgnoreCase(hospital.getHospitalStatus())) {
            throw new IllegalArgumentException("Hospital is not ACTIVE.");
        }

        CheckInResponse checkIn = checkInRepository.getCheckInById(checkInId, hospitalId);
        CheckInValidator.validate(checkIn);

        List<SaleResponse> saleResponses = saleRepository.listAllSaleByCheckIn(checkInId, hospitalId);
        if (saleResponses == null) {
            throw new IllegalArgumentException("No sales found for check-in " + checkInId + " at hospital " + hospitalId);
        }
        return saleResponses;
    }
}
