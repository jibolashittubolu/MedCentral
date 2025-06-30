package com.example.medcentral.feature.auditpatient.repository.database.query;

public class PatientQuery {

    public static final String CREATE_PATIENT = """
            INSERT INTO GROUP1_PATIENT (
                patientFirstName, patientLastName, patientOtherNames, patientDateOfBirth,
                patientGender, patientPhone, patientEmail, patientAddress, patientCountry,
                patientEmergencyContactName, patientEmergencyContactPhone, patientBloodGroup,
                patientGenotype, patientRegisteredAt, patientAccessScope
                )
            VALUES (:patientFirstName, :patientLastName, :patientOtherNames, :patientDateOfBirth,
                :patientGender, :patientPhone, :patientEmail, :patientAddress, :patientCountry,
                :patientEmergencyContactName, :patientEmergencyContactPhone, :patientBloodGroup,
                :patientGenotype, :patientRegisteredAt, COALESCE(NULLIF(:patientAccessScope, ''), 'NATIONAL'));
            """;

    public static final String UPDATE_PATIENT = """
            UPDATE GROUP1_PATIENT
            SET
                patientFirstName = COALESCE(NULLIF(:patientFirstName, ''), patientFirstName),
                patientLastName = COALESCE(NULLIF(:patientLastName, ''), patientLastName),
                patientOtherNames = COALESCE(NULLIF(:patientOtherNames, ''), patientOtherNames),
                patientDateOfBirth = COALESCE(:patientDateOfBirth, patientDateOfBirth),
                patientGender = COALESCE(NULLIF(:patientGender, ''), patientGender),
                patientPhone = COALESCE(NULLIF(:patientPhone, ''), patientPhone),
                patientEmail = COALESCE(NULLIF(:patientEmail, ''), patientEmail),
                patientAddress = COALESCE(NULLIF(:patientAddress, ''), patientAddress),
                patientCountry = COALESCE(NULLIF(:patientCountry, ''), patientCountry),
                patientEmergencyContactName = COALESCE(NULLIF(:patientEmergencyContactName, ''), patientEmergencyContactName),
                patientEmergencyContactPhone = COALESCE(NULLIF(:patientEmergencyContactPhone, ''), patientEmergencyContactPhone),
                patientBloodGroup = COALESCE(NULLIF(:patientBloodGroup, ''), patientBloodGroup),
                patientGenotype = COALESCE(NULLIF(:patientGenotype, ''), patientGenotype),
                patientRegisteredAt = COALESCE(:patientRegisteredAt, patientRegisteredAt),
                patientAccessScope = COALESCE(NULLIF(:patientAccessScope, ''), patientAccessScope),
                patientUpdatedAt = GETDATE()
            WHERE patientId = :patientId
            """;

    public static final String GET_PATIENT_BY_ID = """
            SELECT  patientFirstName, patientLastName, patientOtherNames, patientDateOfBirth,
                    patientGender, patientPhone, patientEmail, patientAddress, patientCountry,
                    patientEmergencyContactName, patientEmergencyContactPhone, patientBloodGroup,
                    patientGenotype, patientRegisteredAt, patientAccessScope
            FROM GROUP1_PATIENT
            WHERE patientId = :patientId
                AND patientStatus <> 'DELETED'
            """;

    public static final String GET_ALL_PATIENT = """
            SELECT  patientFirstName, patientLastName, patientOtherNames, patientDateOfBirth,
                    patientGender, patientPhone, patientEmail, patientAddress, patientCountry,
                    patientEmergencyContactName, patientEmergencyContactPhone, patientBloodGroup,
                    patientGenotype, patientRegisteredAt, patientAccessScope
            FROM GROUP1_PATIENT
            WHERE patientStatus <> 'DELETED'
            """;

    public static final String GET_PATIENT_BY_SEARCH_QUERY = """
            SELECT  patientFirstName, patientLastName, patientOtherNames, patientDateOfBirth,
                    patientGender, patientPhone, patientEmail, patientAddress, patientCountry,
                    patientEmergencyContactName, patientEmergencyContactPhone, patientBloodGroup,
                    patientGenotype, patientRegisteredAt, patientAccessScope
            FROM GROUP1_PATIENT
            WHERE patientStatus <> 'DELETED'
                AND (
                    LOWER(patientFirstName) LIKE LOWER(:searchQuery) OR
                    LOWER(patientLastName) LIKE LOWER(:searchQuery) OR
                    LOWER(patientOtherNames) LIKE LOWER(:searchQuery) OR
                    LOWER(patientGender) LIKE LOWER(:searchQuery) OR
                    LOWER(patientEmail) LIKE LOWER(:searchQuery) OR
                    LOWER(patientAddress) LIKE LOWER(:searchQuery) OR
                    LOWER(patientCountry) LIKE LOWER(:searchQuery) OR
                    LOWER(patientEmergencyContactName) LIKE LOWER(:searchQuery) OR
                    LOWER(patientBloodGroup) LIKE LOWER(:searchQuery) OR
                    LOWER(patientGenotype) LIKE LOWER(:searchQuery)
                    )
            """;

    public static final String DELETE_PATIENT_BY_ID = """
            UPDATE GROUP1_PATIENT
            SET patientStatus = 'DELETED'
            WHERE patientId = :patientId
            """;

    public static final String GET_CHECKINS_BY_PATIENT_ID = """
            SELECT
                p.patientFirstName,
                p.patientLastName,
                c.checkInId,
                h.hospitalName,
                c.checkInReason,
                c.checkInDiagnosis,
                c.checkInPatientNotes
            FROM
                GROUP1_CHECKIN c
            JOIN
                GROUP1_PATIENT p ON c.checkInPatientId = p.patientId
            JOIN
                GROUP1_HOSPITAL h ON c.checkInHospitalId = h.hospitalId
            WHERE
                c.checkInPatientId = :patientId
            """;
}
