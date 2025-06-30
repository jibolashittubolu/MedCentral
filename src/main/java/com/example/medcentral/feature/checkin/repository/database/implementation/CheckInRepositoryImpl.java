package com.example.medcentral.feature.checkin.repository.database.implementation;

import com.example.medcentral.feature.checkin.mapper.CheckInResponseMapper;
import com.example.medcentral.feature.checkin.model.entity.CheckIn;
//import com.example.medcentral.feature.checkin.model.entity.Staff;
import com.example.medcentral.feature.checkin.model.response.CheckInResponse;
import com.example.medcentral.feature.checkin.repository.database.interfaces.CheckInRepository;
import com.example.medcentral.feature.checkin.repository.database.query.CheckInQuery;
//import com.example.medcentral.feature.checkin.repository.database.query.CheckInStaffQuery;
//import com.example.medcentral.feature.checkin.repository.database.query.StaffQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class CheckInRepositoryImpl implements CheckInRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CheckInRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    @Transactional
    public int createCheckIn(CheckIn checkIn, UUID staffId) {
        UUID checkInId = UUID.randomUUID();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("staffId", staffId)
                .addValue("checkInPatientId", checkIn.getCheckInPatientId())
                .addValue("checkInHospitalId", checkIn.getCheckInHospitalId())
                .addValue("checkInAppointmentId", checkIn.getCheckInAppointmentId())
                .addValue("checkInReason", checkIn.getCheckInReason())
                .addValue("checkInStatus", checkIn.getCheckInStatus())
                .addValue("checkInPatientNotes", checkIn.getCheckInPatientNotes());

        int rowInserted = jdbcTemplate.update(CheckInQuery.CREATE_CHECKIN, params);
//        jdbcTemplate.update(CheckInStaffQuery.ASSIGN_STAFF_TO_CHECKIN, params);
        return rowInserted;
    }

    @Override
    @Transactional
    public int createCheckInFromAppointment(UUID appointmentId, UUID staffId) {
        UUID checkInId = UUID.randomUUID();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("staffId", staffId)
                .addValue("appointmentId", appointmentId);

        int rowInserted = jdbcTemplate.update(CheckInQuery.CREATE_CHECKIN_FROM_APPOINTMENT, params);
        UUID appointmentStaffId = jdbcTemplate.queryForObject(CheckInQuery.GET_STAFF_FOR_APPOINTMENT, params, UUID.class);
//        jdbcTemplate.update(CheckInStaffQuery.ASSIGN_STAFF_TO_CHECKIN, params);
        params.addValue("appointmentStaffId", appointmentStaffId);
//        jdbcTemplate.update(CheckInStaffQuery.ASSIGN_APPOINTED_STAFF_TO_CHECKIN, params);
        return rowInserted;
    }

    @Override
    public int updateCheckInStatusAndDiagnosis(String checkInDiagnosis, UUID staffId, UUID checkInId, String checkInStatus, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("staffId", staffId)
                .addValue("checkInDiagnosis", checkInDiagnosis)
                .addValue("checkInStatus", checkInStatus)
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.update(CheckInQuery.UPDATE_CHECKIN_DIAGNOSIS_AND_STATUS, params);
    }

//    private Staff getStaffById(UUID staffId) {
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("staffId", staffId);
//
//        List<Staff> staffList = jdbcTemplate.query(
//                StaffQuery.GET_STAFF_NAME_AND_ROLE_BY_ID,
//                params,
//                (rs, rowNum) -> Staff.builder()
//                        .staffId(staffId)
//                        .staffFirstName(rs.getString("staffFirstName"))
//                        .staffLastName(rs.getString("staffLastName"))
//                        .staffRole(rs.getString("staffRole"))
//                        .build()
//        );
//
//        return staffList.isEmpty() ? null : staffList.get(0);
//    }

    @Override
    public int addCheckInPatientNotes(UUID checkInId, UUID hospitalId, String patientNotes, UUID staffId) {
        LocalDateTime currentDate = LocalDateTime.now();
//        Staff staff = getStaffById(staffId);
//        String formattedNote = String.format(
//                                            "\nAt %s, %s %s %s :%s added:\n%s\n",
//                                            currentDate,
//                                            staff.getStaffRole(),
//                                            staff.getStaffFirstName(),
//                                            staff.getStaffLastName(),
//                                            staffId,
//                                            patientNotes
//                                         );

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("hospitalId", hospitalId)
//                .addValue("checkInPatientNotes", formattedNote)
                .addValue("staffId", staffId);

        return jdbcTemplate.update(CheckInQuery.ADD_PATIENT_NOTES, params);
    }

    @Override
    public CheckInResponse getCheckInById(UUID checkInId, UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("checkInId", checkInId)
                .addValue("hospitalId", hospitalId);

        List<CheckInResponse> results = jdbcTemplate.query(CheckInQuery.GET_CHECKIN_BY_ID, params, new CheckInResponseMapper());

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<CheckInResponse> getAllCheckIns(UUID hospitalId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hospitalId", hospitalId);

        return jdbcTemplate.query(CheckInQuery.GET_ALL_CHECKINS_BY_HOSPITAL_ID, params,
                new CheckInResponseMapper());
    }

    @Override
    public List<CheckInResponse> getAllCheckInsByPatientId(UUID hospitalId, UUID patientId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hospitalId", hospitalId)
                .addValue("patientId", patientId);

        return jdbcTemplate.query(CheckInQuery.GET_ALL_CHECKINS_BY_PATIENT_ID_AND_HOSPITAL, params,
                new CheckInResponseMapper());
    }

    @Override
    public List<CheckInResponse> getActiveCheckInsByPatientId(UUID hospitalId, UUID patientId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hospitalId", hospitalId)
                .addValue("patientId", patientId);
        return jdbcTemplate.query(CheckInQuery.GET_ALL_ACTIVE_CHECKINS_BY_PATIENT_ID_AND_HOSPITAL, params,
                new CheckInResponseMapper());
    }

    @Override
    public List<CheckInResponse> listAllCheckInStaffParticipatedIn(UUID hospitalId, UUID staffId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hospitalId", hospitalId)
                .addValue("staffId", staffId);

        return jdbcTemplate.query(CheckInQuery.LIST_ALL_CHECKIN_FOR_STAFF, params,
                new CheckInResponseMapper());
    }
}