package com.example.medcentral.feature.sale.repository.database.implementation;

import com.example.medcentral.feature.sale.model.response.SaleResponse;
import com.example.medcentral.feature.sale.repository.database.interfaces.SaleRepository;
import com.example.medcentral.feature.sale.repository.database.query.SaleQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SaleRepositoryImpl implements SaleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SaleRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createSale(UUID checkInId, UUID staffId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("staffId", staffId)
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.update(SaleQuery.CREATE_SALE, params);
    }

    @Override
    public SaleResponse getSaleById(UUID saleId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("saleId", saleId)
                .addValue("hospitalId", hospitalId);

        List<SaleResponse> results = jdbcTemplate.query(SaleQuery.GET_SALE_BY_ID, params,
                (rs, rowNum) -> SaleResponse.builder()
                        .checkInId(UUID.fromString(rs.getString("checkInId")))
                        .handledBy(rs.getString("handledBy"))
                        .saleTotal(rs.getBigDecimal("saleTotal"))
                        .saleStatus(rs.getString("saleStatus"))
                        .medicineSold(rs.getString("medicineSold"))
                        .build());

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<SaleResponse> listAllSaleByCheckIn(UUID checkInId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.query(SaleQuery.GET_ALL_SALE_BY_CHECKIN_ID, params,
                (rs, rowNum) -> SaleResponse.builder()
                        .checkInId(UUID.fromString(rs.getString("checkInId")))
                        .handledBy(rs.getString("handledBy"))
                        .saleTotal(rs.getBigDecimal("saleTotal"))
                        .saleStatus(rs.getString("saleStatus"))
                        .medicineSold(rs.getString("medicineSold"))
                        .build());
    }

//    @Override
//    public List<Prescription> listAllPrescriptionsInSale(UUID saleId, UUID hospitalId) {
//        return List.of();
//    }
}
