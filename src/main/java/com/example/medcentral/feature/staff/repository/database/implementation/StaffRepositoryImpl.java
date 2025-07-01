package com.example.medcentral.feature.staff.repository.database.implementation;

import com.example.medcentral.feature.staff.mapper.StaffResponseMapper;
import com.example.medcentral.feature.staff.model.entity.Staff;
import com.example.medcentral.feature.staff.model.response.StaffResponse;
import com.example.medcentral.feature.staff.repository.database.interfaces.StaffRepository;
import com.example.medcentral.feature.staff.repository.database.query.StaffQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StaffRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createStaff(Staff staff) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffFirstName", staff.getStaffFirstName())
                .addValue("staffLastName", staff.getStaffLastName())
                .addValue("staffOtherNames", staff.getStaffOtherNames())
                .addValue("staffDateOfBirth", staff.getStaffDateOfBirth())
                .addValue("staffGender", staff.getStaffGender())
                .addValue("staffPhone", staff.getStaffPhone())
                .addValue("staffEmail", staff.getStaffEmail())
                .addValue("staffAddress", staff.getStaffAddress())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffHospitalId", staff.getStaffHospitalId());

        return jdbcTemplate.update(StaffQuery.CREATE_STAFF, params);
    }


    @Override
    public int updateStaff(Staff staff) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", staff.getStaffId())
                .addValue("staffFirstName", staff.getStaffFirstName())
                .addValue("staffLastName", staff.getStaffLastName())
                .addValue("staffOtherNames", staff.getStaffOtherNames())
                .addValue("staffDateOfBirth", staff.getStaffDateOfBirth())
                .addValue("staffGender", staff.getStaffGender())
                .addValue("staffPhone", staff.getStaffPhone())
                .addValue("staffEmail", staff.getStaffEmail())
                .addValue("staffAddress", staff.getStaffAddress())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffHospitalId", staff.getStaffHospitalId())
                .addValue("staffStatus", staff.getStaffStatus());

        return jdbcTemplate.update(StaffQuery.UPDATE_STAFF, params);
    }

    @Override
    public StaffResponse getStaffById(UUID staffId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", staffId);

        List<StaffResponse> results = jdbcTemplate.query(StaffQuery.GET_STAFF_BY_ID, params,
                new StaffResponseMapper());

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<StaffResponse> getStaffByHosital(UUID staffHospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffHospitalId", staffHospitalId);

        return jdbcTemplate.query(StaffQuery.GET_STAFF_BY_HOSPITAL, params,
                new StaffResponseMapper());
    }

    @Override
    public int deleteStaff(UUID staffId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", staffId);

        return jdbcTemplate.update(StaffQuery.DELETE_STAFF_BY_ID, params);
    }
}
