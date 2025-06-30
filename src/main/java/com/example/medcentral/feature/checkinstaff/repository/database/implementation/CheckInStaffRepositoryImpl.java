package com.example.medcentral.feature.checkinstaff.repository.database.implementation;

import com.example.medcentral.feature.checkinstaff.mapper.CheckInStaffResponseMapper;
import com.example.medcentral.feature.checkinstaff.model.response.CheckInStaffResponse;
import com.example.medcentral.feature.checkinstaff.repository.database.interfaces.CheckInStaffRepository;
import com.example.medcentral.feature.checkinstaff.repository.database.query.CheckInStaffQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CheckInStaffRepositoryImpl implements CheckInStaffRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CheckInStaffRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int assignStaffToCheckIn(UUID checkInId, UUID staffId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("staffId", staffId);

        return jdbcTemplate.update(CheckInStaffQuery.ASSIGN_STAFF_TO_CHECKIN, params);
    }

    @Override
    public int[] assignMultipleStaffToCheckIn(UUID checkInId, UUID hospitalId, List<UUID> staffIds) {
        List<Map<String, Object>> batchValues = new ArrayList<>();

        for (UUID staffId : staffIds) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("staffId", staffId);
            paramMap.put("hospitalId", hospitalId);
            paramMap.put("checkInId", checkInId);
            paramMap.put("checkInStaffStatus", "ACTIVE"); // important
            batchValues.add(paramMap);
        }

        return jdbcTemplate.batchUpdate(CheckInStaffQuery.ASSIGN_STAFF_TO_CHECKIN, batchValues.toArray(new Map[0]));
    }

    @Override
    public List<CheckInStaffResponse> listAllCheckInStaff(UUID checkInId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.query(CheckInStaffQuery.LIST_ALL_CHECKIN_STAFF, params,
                new CheckInStaffResponseMapper());
    }
}