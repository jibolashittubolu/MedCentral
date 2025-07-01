package com.example.v_medcentral.feature.staff.repository.database.query;

public class StaffQuery {

    public static final String CREATE_STAFF = """
            INSERT INTO GROUP1_STAFF (
                staffFirstName,
                staffLastName,
                staffOtherNames,
                staffDateOfBirth,
                staffGender,
                staffPhone,
                staffEmail,
                staffAddress,
                staffRole,
                staffDepartment,
                staffHospitalId
            ) VALUES (
                :staffFirstName,
                :staffLastName,
                :staffOtherNames,
                :staffDateOfBirth,
                :staffGender,
                :staffPhone,
                :staffEmail,
                :staffAddress,
                :staffRole,
                :staffDepartment,
                :staffHospitalId
            );
            """;

    public static final String GET_STAFF_NAME_AND_ROLE_BY_ID = """
    SELECT staffFirstName, staffLastName, staffRole
    FROM GROUP1_STAFF
    WHERE staffId = :staffId
    """;

    public static final String UPDATE_STAFF = """
            UPDATE GROUP1_STAFF
            SET
                staffFirstName = COALESCE(NULLIF(:staffFirstName, ''), staffFirstName),
                staffLastName = COALESCE(NULLIF(:staffLastName, ''), staffLastName),
                staffOtherNames = COALESCE(NULLIF(:staffOtherNames, ''), staffOtherNames),
                staffDateOfBirth = COALESCE(:staffDateOfBirth), staffDateOfBirth),
                staffGender = COALESCE(NULLIF(:staffGender, ''), staffGender),
                staffPhone = COALESCE(NULLIF(:staffPhone, ''), staffPhone),
                staffEmail = COALESCE(NULLIF(:staffEmail, ''), staffEmail),
                staffAddress = COALESCE(NULLIF(:staffAddress, ''), staffAddress),
                staffRole = COALESCE(NULLIF(:staffRole, ''), staffRole),
                staffDepartment = COALESCE(NULLIF(:staffDepartment, ''), staffDepartment),
                staffHospitalId = COALESCE(:staffHospitalId, staffHospitalId),
                staffStatus = COALESCE(NULLIF(:staffStatus, ''), staffStatus)
            WHERE staffId = :staffId;
            """;

    public static final String GET_STAFF_BY_ID = """
            SELECT s.staffFirstName, s.staffLastName, s.staffRole, h.hospitalName, s.staffStatus
            FROM GROUP1_STAFF s
            JOIN
                GROUP1_HOSPITAL h ON s.staffHospitalId = h.hospitalId
            WHERE s.staffId = :staffId
            """;

    public static final String GET_STAFF_BY_HOSPITAL = """
            SELECT s.staffFirstName, s.staffLastName, s.staffRole, h.hospitalName, s.staffStatus
            FROM GROUP1_STAFF s
            JOIN
                GROUP1_HOSPITAL h ON s.staffHospitalId = h.hospitalId
            WHERE s.staffHospitalId = :staffHospitalId
            """;

    public static final String DELETE_STAFF_BY_ID = """
            UPDATE GROUP1_STAFF
            SET
                staffStatus = 'DELETED'
            WHERE staffId = :staffId;
            """;
}
