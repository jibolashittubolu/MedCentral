package com.example.medcentral.feature.checkin.repository.database.query;

public class CheckInQuery {

    public static final String CREATE_CHECKIN = """
            INSERT INTO GROUP1_CHECKIN (
                checkInId,
                checkInPatientId,
                checkInHospitalId,
                checkInAppointmentId,
                checkInReason,
                checkInStatus,
                checkInPatientNotes
            )
            VALUES (
                :checkInId,
                :checkInPatientId,
                :checkInHospitalId,
                :checkInAppointmentId,
                :checkInReason,
                COALESCE(NULLIF(:checkInStatus, ''), 'ACTIVE'),
                COALESCE(
                    NULLIF(:checkInPatientNotes, ''),
                    CONCAT(
                        'Patient ', :checkInPatientId,
                        ' came for a ', :checkInReason,
                        ' check-in at ', CONVERT(varchar, GETDATE(), 120),
                        CHAR(13) + CHAR(10) + CHAR(13) + CHAR(10)
                        )
                    )
                )
            """;

    public static final String CREATE_CHECKIN_FROM_APPOINTMENT = """
            INSERT INTO GROUP1_CHECKIN (
                checkInPatientId,
                checkInHospitalId,
                checkInAppointmentId,
                checkInReason,
                checkInStatus,
                checkInPatientNotes
                )
            SELECT
                a.appointmentPatientId,
                a.appointmentHospitalId,
                a.appointmentId,
                a.appointmentReason,
                'ACTIVE',
                CONCAT(
                    'Patient ', a.appointmentPatientId,
                    ' checked in for appointment (', a.appointmentReason, ') at ',
                    CONVERT(varchar, GETDATE(), 120)
                )
            FROM
                GROUP1_APPOINTMENT a
            WHERE
                a.appointmentId = :appointmentId
            """;

    public static final String GET_STAFF_FOR_APPOINTMENT = """
            SELECT appointmentStaffId
            FROM GROUP1_APPOINTMENT
            WHERE appointmentId = :appointmentId
            """;

    public static final String UPDATE_CHECKIN_DIAGNOSIS_AND_STATUS  = """
            UPDATE GROUP1_CHECKIN
            SET
                checkInDiagnosis = :checkInDiagnosis,
                checkInStatus = COALESCE(NULLIF(:checkInStatus, ''), 'ACTIVE'),
                checkInUpdatedAt = GETDATE()
            WHERE checkInId = :checkInId
                AND checkInStatus <> 'DELETED'
                AND checkInHospitalId = :hospitalId
            """;

    public static final String ADD_PATIENT_NOTES = """
            UPDATE GROUP1_CHECKIN
            SET
                checkInPatientNotes = COALESCE(checkInPatientNotes, '') + :checkInPatientNotes,
                checkInUpdatedAt = GETDATE()
            WHERE
                checkInId = :checkInId
                AND checkInStatus <> 'DELETED'
            """;

    public static final String GET_CHECKIN_BY_ID = """
            SELECT
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes,
                STRING_AGG(
                    CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')'),
                    ', '
                ) AS staffAssigned
            FROM GROUP1_CHECKIN c
            JOIN GROUP1_PATIENT p ON c.checkInPatientId = p.patientId
            JOIN GROUP1_HOSPITAL h ON c.checkInHospitalId = h.hospitalId
            JOIN GROUP1_CHECKIN_STAFF cs ON c.checkInId = cs.checkInId
            JOIN GROUP1_STAFF s ON cs.staffId = s.staffId
            WHERE
                c.checkInId = :checkInId
                AND c.checkInHospitalId = :hospitalId
                AND c.checkInStatus <> 'DELETED'
            GROUP BY
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes
            """;

    public static final String GET_ALL_CHECKINS_BY_HOSPITAL_ID = """
            SELECT
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes,
                STRING_AGG(
                    CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')'),
                    ', '
                ) AS staffAssigned
            FROM GROUP1_CHECKIN c
            JOIN GROUP1_PATIENT p ON c.checkInPatientId = p.patientId
            JOIN GROUP1_HOSPITAL h ON c.checkInHospitalId = h.hospitalId
            LEFT JOIN GROUP1_CHECKIN_STAFF cs ON c.checkInId = cs.checkInId
            LEFT JOIN GROUP1_STAFF s ON cs.staffId = s.staffId
            WHERE
                c.checkInHospitalId = :hospitalId
                AND c.checkInStatus <> 'DELETED'
            GROUP BY
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes
            """;

    public static final String GET_ALL_CHECKINS_BY_PATIENT_ID_AND_HOSPITAL = """
            SELECT
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes,
                STRING_AGG(
                    CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')'),
                    ', '
                ) AS staffAssigned
            FROM GROUP1_CHECKIN c
            JOIN GROUP1_PATIENT p ON c.checkInPatientId = p.patientId
            JOIN GROUP1_HOSPITAL h ON c.checkInHospitalId = h.hospitalId
            LEFT JOIN GROUP1_CHECKIN_STAFF cs ON c.checkInId = cs.checkInId
            LEFT JOIN GROUP1_STAFF s ON cs.staffId = s.staffId
            WHERE
                c.checkInHospitalId = :hospitalId
                AND c.checkInPatientId = :patientId
                AND c.checkInStatus <> 'DELETED'
            GROUP BY
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes
            """;

    public static final String GET_ALL_ACTIVE_CHECKINS_BY_PATIENT_ID_AND_HOSPITAL = """
            SELECT
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes,
                STRING_AGG(
                    CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')'),
                    ', '
                ) AS staffAssigned
            FROM GROUP1_CHECKIN c
            JOIN GROUP1_PATIENT p ON c.checkInPatientId = p.patientId
            JOIN GROUP1_HOSPITAL h ON c.checkInHospitalId = h.hospitalId
            LEFT JOIN GROUP1_CHECKIN_STAFF cs ON c.checkInId = cs.checkInId
            LEFT JOIN GROUP1_STAFF s ON cs.staffId = s.staffId
            WHERE
                c.checkInHospitalId = :hospitalId
                AND c.checkInPatientId = :patientId
                AND c.checkInStatus = 'ACTIVE'
            GROUP BY
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes
            """;

    public static final String LIST_ALL_CHECKIN_FOR_STAFF = """
            SELECT
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes,
                STRING_AGG(
                    CONCAT(s.staffFirstName, ' ', s.staffLastName, ' (', s.staffRole, ')'),
                    ', '
                ) AS staffAssigned
            FROM GROUP1_CHECKIN_STAFF cs
            JOIN GROUP1_CHECKIN c ON cs.checkInId = c.checkInId
            JOIN GROUP1_PATIENT p ON c.checkInPatientId = p.patientId
            JOIN GROUP1_HOSPITAL h ON c.checkInHospitalId = h.hospitalId
            LEFT JOIN GROUP1_CHECKIN_STAFF cs2 ON c.checkInId = cs2.checkInId
            LEFT JOIN GROUP1_STAFF s ON cs2.staffId = s.staffId
            WHERE
                cs.staffId = :staffId
                AND c.checkInStatus <> 'DELETED'
            GROUP BY
                c.checkInId,
                p.patientFirstName,
                p.patientLastName,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInStatus,
                c.checkInPatientNotes
            """;
}
