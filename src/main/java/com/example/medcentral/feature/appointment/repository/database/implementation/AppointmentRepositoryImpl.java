package com.example.medcentral.feature.appointment.repository.database.implementation;

import com.example.medcentral.feature.appointment.mapper.AppointmentResponseMapper;
import com.example.medcentral.feature.appointment.model.entity.Appointment;
import com.example.medcentral.feature.appointment.model.response.AppointmentResponse;
import com.example.medcentral.feature.appointment.repository.database.interfaces.AppointmentRepository;
import com.example.medcentral.feature.appointment.repository.database.query.AppointmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AppointmentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public int createAppointment(Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointPatientId", appointment.getAppointmentPatientId())
                .addValue("appointmentStaffId", appointment.getAppointmentStaffId())
                .addValue("appointmentHospitalId", appointment.getAppointmentHospitalId())
                .addValue("appointmentDateTime", appointment.getAppointmentDateTime())
                .addValue("appointmentReason", appointment.getAppointmentReason());

        return jdbcTemplate.update(AppointmentQuery.CREATE_APPOINTMENT, params);
    }

    @Override
    public int updateAppointment(Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointment.getAppointmentId())
                .addValue("appointmentStaffId", appointment.getAppointmentStaffId())
                .addValue("appointmentHospitalId", appointment.getAppointmentHospitalId())
                .addValue("appointmentDateTime", appointment.getAppointmentDateTime())
                .addValue("appointmentReason", appointment.getAppointmentReason())
                .addValue("appointmentStatus", appointment.getAppointmentStatus());

        return jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT, params);
    }

    @Override
    public int rescheduleAppointment(Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointment.getAppointmentId())
                .addValue("appointmentDateTime", appointment.getAppointmentDateTime())
                .addValue("appointmentStaffId", null)
                .addValue("appointmentHospitalId", null)
                .addValue("appointmentReason", null)
                .addValue("appointmentStatus", null);

        return jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT, params);
    }

    @Override
    public int cancelAppointment(UUID appointmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId);

        return jdbcTemplate.update(AppointmentQuery.CANCEL_APPOINTMENT, params);
    }

    @Override
    public int deleteAppointment(UUID appointmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId);

        return jdbcTemplate.update(AppointmentQuery.DELETE_APPOINTMENT, params);
    }

    @Override
    public AppointmentResponse getAppointmentById(UUID appointmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId);
        List<AppointmentResponse> results = jdbcTemplate.query(AppointmentQuery.GET_APPOINTMENT_BY_ID, params,
                new AppointmentResponseMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<AppointmentResponse> getAppointmentByPatientId(UUID appointmentPatientId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentPatientId", appointmentPatientId);

        return jdbcTemplate.query(AppointmentQuery.GET_APPOINTMENT_BY_PATIENT_ID, params,
                new AppointmentResponseMapper());
    }
}
