package com.example.medcentral.feature.sale.repository.database.query;

public class SaleQuery {

    public static final String CREATE_SALE = """
            INSERT INTO GROUP1_SALES (
                        saleId,
                        saleCheckInId,
                        saleTotal,
                        saleHandledById,
                        saleStatus,
                        saleCreatedAt,
                        saleUpdatedAt
                        )
            SELECT
                NEWID(),
                p.prescriptionCheckInId,
                SUM(p.prescriptionQuantity * m.medicineUnitPrice),
                :staffId,
                'ACTIVE',
                GETDATE(),
                GETDATE()
            FROM
                GROUP1_PRESCRIPTION p
            JOIN
                GROUP1_MEDICINE m ON p.prescriptionMedicineId = m.medicineId
            WHERE
                p.prescriptionCheckInId = :checkInId
            GROUP BY
                p.prescriptionCheckInId
            """;

    public static final String GET_SALE_BY_ID = """
            SELECT
                s.saleCheckInId AS checkInId,
                CONCAT(sa.staffRole, ' ', sa.staffFirstName, ' ', sa.staffLastName) AS handledBy,
                s.saleTotal,
                s.saleStatus,
                CONCAT(m.medicineName, ' x ', p.prescriptionQuantity) AS medicineSold
            FROM
                GROUP1_SALES s
            JOIN GROUP1_STAFF sa ON s.saleHandledById = sa.staffId
            JOIN GROUP1_PRESCRIPTION p ON s.saleCheckInId = p.prescriptionCheckInId
            JOIN GROUP1_MEDICINE m ON p.prescriptionMedicineId = m.medicineId
            WHERE
                s.saleId = :saleId
            """;


    public static final String GET_ALL_SALE_BY_CHECKIN_ID = """
            SELECT
                s.saleCheckInId AS checkInId,
                CONCAT(sa.staffRole, ' ', sa.staffFirstName, ' ', sa.staffLastName) AS handledBy,
                s.saleTotal,
                s.saleStatus,
                CONCAT(m.medicineName, ' x ', p.prescriptionQuantity) AS medicineSold
            FROM
                GROUP1_SALES s
            JOIN GROUP1_STAFF sa ON s.saleHandledById = sa.staffId
            JOIN GROUP1_PRESCRIPTION p ON s.saleCheckInId = p.prescriptionCheckInId
            JOIN GROUP1_MEDICINE m ON p.prescriptionMedicineId = m.medicineId
            WHERE
                s.saleCheckInId = :saleCheckInId
            """;



    public static final String GET_ALL_PRESCRIPTIONS_IN_SALE = """
            SELECT
                m.medicineName,
                p.prescriptionDosage,
                p.prescriptionQuantity,
                s.staffRole,
                CONCAT(s.staffFirstName, ' ', s.staffLastName) AS staffName,
                p.prescriptionStatus
            FROM
                GROUP1_SALES sa
            JOIN
                GROUP1_PRESCRIPTION p ON sa.saleCheckInId = p.prescriptionCheckInId
            JOIN
                GROUP1_MEDICINE m ON p.prescriptionMedicineId = m.medicineId
            JOIN
                GROUP1_STAFF s ON p.prescriptionPrescribedById = s.staffId
            WHERE
                sa.saleId = :saleId
            """;
}