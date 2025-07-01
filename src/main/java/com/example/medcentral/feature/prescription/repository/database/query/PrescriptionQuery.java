package com.example.medcentral.feature.prescription.repository.database.query;

public class PrescriptionQuery {

    public static final String CREATE_PRESCRIPTION = """
        INSERT INTO GROUP1_PRESCRIPTION
            (prescriptionCheckInId, prescriptionMedicineId, prescriptionPrescribedById, prescriptionDosage, prescriptionQuantity, prescriptionNotes )
        VALUES
            (:prescriptionCheckInId, :prescriptionMedicineId, :prescriptionPrescribedById, :prescriptionDosage, :prescriptionQuantity, :prescriptionNotes )
    """;


    public static final String GET_PRESCRIPTIONS = """
        SELECT * FROM GROUP1_PRESCRIPTION
        WHERE prescriptionStatus != 'DELETED'
    """;


    public static final String GET_PRESCRIPTION_BY_ID = """
        SELECT * FROM GROUP1_PRESCRIPTION
        WHERE prescriptionId = :prescriptionId
        AND prescriptionStatus != 'DELETED'
    """;

    public static final String UPDATE_PRESCRIPTION = """
        UPDATE GROUP1_PRESCRIPTION
            SET
                prescriptionDosage = COALESCE(:prescriptionDosage, prescriptionDosage),
                prescriptionQuantity = COALESCE(:prescriptionQuantity, prescriptionQuantity),
                prescriptionStatus = COALESCE(:prescriptionStatus, prescriptionStatus),
                prescriptionNotes = COALESCE(:prescriptionNotes, prescriptionNotes),
                prescriptionUpdatedAt = CASE
                    WHEN
                        (
                        COALESCE(:prescriptionDosage, prescriptionDosage) <> prescriptionDosage OR
                        COALESCE(:prescriptionQuantity, prescriptionQuantity) <> prescriptionQuantity OR
                        COALESCE(:prescriptionStatus, prescriptionStatus) <> prescriptionStatus OR
                        COALESCE(:prescriptionNotes, prescriptionNotes) <> prescriptionNotes
                        )
                    THEN GETDATE()
                    ELSE prescriptionUpdatedAt
                END
        WHERE prescriptionId = :prescriptionId
                  AND prescriptionStatus != 'DELETED';
    """;

    public static final String DELETE_PRESCRIPTION = """
        UPDATE GROUP1_PRESCRIPTION
           SET prescriptionStatus = 'DELETED'
         WHERE prescriptionId = :prescriptionId
         AND prescriptionStatus != 'DELETED';
    """;
}


