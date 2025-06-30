package com.example.medcentral.feature.medicine.repository.database.implementation;

import com.example.medcentral.feature.medicine.mapper.MedicineResponseMapper;
import com.example.medcentral.feature.medicine.model.entity.Medicine;
import com.example.medcentral.feature.medicine.model.response.MedicineResponse;
import com.example.medcentral.feature.medicine.repository.database.interfaces.MedicineRepository;
import com.example.medcentral.feature.medicine.repository.database.query.MedicineQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MedicineRepositoryImpl implements MedicineRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MedicineRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMedicine(Medicine medicine) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicineName", medicine.getMedicineName())
                .addValue("medicineHospitalId", medicine.getMedicineHospitalId())
                .addValue("medicineName", medicine.getMedicineName())
                .addValue("medicineDescription", medicine.getMedicineDescription())
                .addValue("medicineDosageForm", medicine.getMedicineDosageForm())
                .addValue("medicineDosage", medicine.getMedicineDosage())
                .addValue("medicineExpiryDate", medicine.getMedicineExpiryDate())
                .addValue("medicineStockQty", medicine.getMedicineStockQty())
                .addValue("medicineUnitPrice", medicine.getMedicineUnitPrice())
                .addValue("medicineReorderLevel", medicine.getMedicineReorderLevel());

        return jdbcTemplate.update(MedicineQuery.CREATE_MEDICINE, params);
    }

    @Override
    public int updateMedicine(Medicine medicine) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicineId", medicine.getMedicineId())
                .addValue("medicineName", medicine.getMedicineName())
                .addValue("medicineHospitalId", medicine.getMedicineHospitalId())
                .addValue("medicineName", medicine.getMedicineName())
                .addValue("medicineDescription", medicine.getMedicineDescription())
                .addValue("medicineDosageForm", medicine.getMedicineDosageForm())
                .addValue("medicineDosage", medicine.getMedicineDosage())
                .addValue("medicineExpiryDate", medicine.getMedicineExpiryDate())
                .addValue("medicineStockQty", medicine.getMedicineStockQty())
                .addValue("medicineUnitPrice", medicine.getMedicineUnitPrice())
                .addValue("medicineReorderLevel", medicine.getMedicineReorderLevel());

        return jdbcTemplate.update(MedicineQuery.UPDATE_MEDICINE, params);
    }

    @Override
    public int updateMedicineQuantity(int medicineQuantity, UUID hospitalId, UUID medicineId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicineHospitalId", hospitalId)
                .addValue("medicineId", medicineId)
                .addValue("medicineStockQty", medicineQuantity);

        return jdbcTemplate.update(MedicineQuery.UPDATE_MEDICINE_QUANTITY, params);
    }

    @Override
    public MedicineResponse getMedicineById(UUID medicineId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicineId", medicineId)
                .addValue("medicineHospitalId", hospitalId);

        List<MedicineResponse> results = jdbcTemplate.query(
                MedicineQuery.GET_MEDICINE_BY_ID, params, new MedicineResponseMapper());

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<MedicineResponse> getMedicineByHospitalId(UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.query(MedicineQuery.GET_MEDICINE_BY_HOSPITAL_ID, params,
                new MedicineResponseMapper());
    }

    @Override
    public List<MedicineResponse> getMedicineWithLowStock(UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.query(MedicineQuery.GET_MEDICINE_WITH_LOW_STOCK, params,
                new MedicineResponseMapper());
    }

    @Override
    public List<MedicineResponse> getMedicineByName(String medicineName, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicineName", "%"+medicineName+"%")
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.query(MedicineQuery.GET_MEDICINE_BY_NAME, params,
                new MedicineResponseMapper());
    }

    @Override
    public int deleteMedicineById(UUID medicineId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicineId", medicineId)
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.update(MedicineQuery.DELETE_MEDICINE_BY_ID, params);
    }
}
