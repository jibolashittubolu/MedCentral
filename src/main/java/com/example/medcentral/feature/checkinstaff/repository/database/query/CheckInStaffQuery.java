package com.example.medcentral.feature.checkinstaff.repository.database.query;

public class CheckInStaffQuery {

    public static final String ASSIGN_STAFF_TO_CHECKIN = """
            INSERT INTO GROUP1_CHECKIN_STAFF (
                checkInId,
                staffId,
                checkInStaffRole,
                checkInStaffStatus
            )
            SELECT
                :checkInId,
                s.staffId,
                s.staffRole,
                'ACTIVE'
            FROM
                GROUP1_STAFF s
            WHERE
                s.staffId = :staffId
            """;

    public static final String ASSIGN_APPOINTED_STAFF_TO_CHECKIN = """
            INSERT INTO GROUP1_CHECKIN_STAFF (
                checkInId,
                staffId,
                checkInStaffRole,
                checkInStaffStatus
            )
            SELECT
                :checkInId,
                s.staffId,
                s.staffRole,
                'ACTIVE'
            FROM
                GROUP1_STAFF s
            WHERE
                s.staffId = :appointmentStaffId
            """;

    public static final String LIST_ALL_CHECKIN_STAFF = """
            SELECT
                s.staffFirstName, s.staffLastName, s.staffRole,
                s.staffId
            FROM GROUP1_CHECKIN_STAFF cs
            JOIN GROUP1_STAFF s on cs.staffId = s.staffId
            WHERE
                cs.checkInId = :checkInId
            """;

//    public static final String LIST_ALL_CHECKIN_FOR_STAFF = """
//            SELECT
//                p.patientFirstName, p.patientLastName,
//                c.checkInId, c.checkInReason, c.checkInDiagnosis
//            FROM GROUP1_CHECKIN_STAFF cs
//            JOIN GROUP1_CHECKIN c on cs.checkInId = c.checkInId
//            JOIN GROUP1_PATIENT p on c.checkInPatientId = p.patientId
//            WHERE cs.staffId = :staffId
//            """;
}
