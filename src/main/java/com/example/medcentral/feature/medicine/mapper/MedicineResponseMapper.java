package com.example.medcentral.feature.medicine.mapper;

import com.example.medcentral.feature.medicine.model.response.MedicineResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class MedicineResponseMapper implements RowMapper<MedicineResponse> {
    @Override
    public MedicineResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MedicineResponse.builder()
                .hospitalName(rs.getString("hospitalName"))
                .medicineId(UUID.fromString(rs.getString("medicineId")))
                .medicineName(rs.getString("medicineName"))
                .medicineDescription(rs.getString("medicineDescription"))
                .medicineDosageForm(rs.getString("medicineDosageForm"))
                .medicineDosage(rs.getString("medicineDosage"))
                .medicineExpiryDate(rs.getString("medicineExpiryDate"))
                .medicineStockQty(rs.getString("medicineStockQty"))
                .medicineUnitPrice(rs.getString("medicineUnitPrice"))
                .medicineReorderLevel(String.valueOf(rs.getString("medicineReorderLevel")))
                .build();
    }
}
