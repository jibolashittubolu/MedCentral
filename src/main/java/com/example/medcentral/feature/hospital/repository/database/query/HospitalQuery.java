package com.example.medcentral.feature.hospital.repository.database.query;

public class HospitalQuery {

    public static final String CREATE_HOSPITAL = """
        INSERT INTO GROUP1_HOSPITAL
            (hospitalName, hospitalAddress, hospitalCity, hospitalState, hospitalPhone, hospitalEmail )
        VALUES
            (:hospitalName, :hospitalAddress, :hospitalCity, :hospitalState, :hospitalPhone, :hospitalEmail )
    """;


    public static final String GET_HOSPITALS = """
        SELECT * FROM GROUP1_HOSPITAL
        WHERE hospitalStatus != 'deleted'
    """;


    public static final String GET_HOSPITAL_BY_ID = """
        SELECT * FROM GROUP1_HOSPITAL
        WHERE hospitalId = :hospitalId
        AND hospitalStatus != 'deleted'
    """;

    public static final String UPDATE_HOSPITAL = """
        UPDATE GROUP1_HOSPITAL
            SET
                hospitalName = COALESCE(:hospitalName, hospitalName),
                hospitalAddress = COALESCE(:hospitalAddress, hospitalAddress),
                hospitalCity = COALESCE(:hospitalCity, hospitalCity),
                hospitalState = COALESCE(:hospitalState, hospitalState),
                hospitalPhone = COALESCE(:hospitalPhone, hospitalPhone),
                hospitalEmail = COALESCE(:hospitalEmail, hospitalEmail),
                hospitalUpdatedAt = CASE
                    WHEN
                        ( 
                        COALESCE(:hospitalName, hospitalName) <> hospitalName OR
                        COALESCE(:hospitalAddress, hospitalAddress) <> hospitalAddress OR
                        COALESCE(:hospitalCity, hospitalCity) <> hospitalCity OR
                        COALESCE(:hospitalState, hospitalState) <> hospitalState OR
                        COALESCE(:hospitalPhone, hospitalPhone) <> hospitalPhone OR
                        COALESCE(:hospitalEmail, hospitalEmail) <> hospitalEmail
                        )
                    THEN GETDATE()
                    ELSE hospitalUpdatedAt
                END
        WHERE hospitalId = :hospitalId
                  AND hospitalStatus != 'DELETED';
    """;

    public static final String DELETE_HOSPITAL = """
        UPDATE GROUP1_HOSPITAL
           SET hospitalStatus = 'DELETED'
         WHERE hospitalId = :hospitalId
         AND hospitalStatus != 'DELETED';
    """;
}


