package com.example.v_medcentral.feature.appointment.repository.database.query;

public class AppointmentQuery {

    public static final String CREATE_APPOINTMENT = """
            INSERT INTO GROUP1_APPOINTMENT (
                appointmentPatientId,
                appointmentStaffId,
                appointmentHospitalId,
                appointmentDateTime,
                appointmentReason
            )
            VALUES (
                :appointmentPatientId,
                :appointmentStaffId,
                :appointmentHospitalId,
                :appointmentDateTime,
                :appointmentReason
            )
            """;

    public static final String UPDATE_APPOINTMENT = """
            UPDATE GROUP1_APPOINTMENT
            SET
                appointmentPatientId    = COALESCE(:appointmentPatientId, appointmentPatientId),
                appointmentStaffId      = COALESCE(:appointmentStaffId, appointmentStaffId),
                appointmentHospitalId   = COALESCE(:appointmentHospitalId, appointmentHospitalId),
                appointmentDateTime     = COALESCE(:appointmentDateTime, appointmentDateTime),
                appointmentReason       = COALESCE(:appointmentReason, appointmentReason),
                appointmentStatus       = COALESCE(:appointmentStatus, appointmentStatus),
                appointmentUpdatedAt    = GETDATE()
            WHERE
                appointmentId = :appointmentId
            """;

    public static final String CANCEL_APPOINTMENT = """
            UPDATE GROUP1_APPOINTMENT
            SET
                appointmentStatus       = 'CANCELLED',
                appointmentUpdatedAt    = GETDATE()
            WHERE
                appointmentId = :appointmentId
            """;

    public static final String DELETE_APPOINTMENT = """
            UPDATE GROUP1_APPOINTMENT
            SET
                appointmentStatus       = 'DELETED',
                appointmentUpdatedAt    = GETDATE()
            WHERE
                appointmentId = :appointmentId
            """;

    public static final String GET_APPOINTMENT_BY_ID = """
            SELECT
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')') AS staffAssigned,
                a.appointmentId,
                a.appointmentReason,
                a.appointmentStatus
            FROM GROUP1_APPOINTMENT a
            JOIN GROUP1_HOSPITAL h ON a.appointmentHospitalId = h.hospitalId
            JOIN GROUP1_PATIENT p ON a.appointmentPatientId = p.patientId
            JOIN GROUP1_STAFF s ON a.appointmentStaffId = s.staffId
            WHERE a.appointmentId = :appointmentId
            """;


    public static final String GET_APPOINTMENT_BY_PATIENT_ID = """
            SELECT
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')') AS staffAssigned,
                a.appointmentId,
                a.appointmentReason,
                a.appointmentStatus
            FROM GROUP1_APPOINTMENT a
            JOIN GROUP1_HOSPITAL h ON a.appointmentHospitalId = h.hospitalId
            JOIN GROUP1_PATIENT p ON a.appointmentPatientId = p.patientId
            JOIN GROUP1_STAFF s ON a.appointmentStaffId = s.staffId
            WHERE a.appointmentPatientId = :appointmentPatientId
            """;

}
