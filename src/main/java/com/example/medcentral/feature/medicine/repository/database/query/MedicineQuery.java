package com.example.medcentral.feature.medicine.repository.database.query;

public class MedicineQuery {

    public static final String CREATE_MEDICINE = """
            INSERT INTO GROUP1_MEDICINE(
                medicineHospitalId, medicineName, medicineDescription,
                medicineDosageForm, medicineDosage, medicineExpiryDate,
                medicineStockQty, medicineUnitPrice, medicineReorderLevel
                )
            VALUES(:medicineHospitalId, :medicineName, :medicineDescription,
                    :medicineDosageForm, :medicineDosage, :medicineExpiryDate,
                    :medicineStockQty, :medicineUnitPrice, :medicineReorderLevel
                    )
            """;

    public static final String UPDATE_MEDICINE = """
            UPDATE GROUP1_MEDICINE
            SET
               medicineName = COALESCE(NULLIF(:medicineName, ''), medicineName),
               medicineHospitalId = COALESCE(:medicineHospitalId, medicineHospitalId),
               medicineDescription = COALESCE(NULLIF(:medicineDescription, ''), medicineDescription),
               medicineDosageForm = COALESCE(NULLIF(:medicineDosageForm, ''), medicineDosageForm),
               medicineDosage = COALESCE(NULLIF(:medicineDosage, ''), medicineDosage),
               medicineExpiryDate = COALESCE(:medicineExpiryDate, medicineExpiryDate),
               medicineStockQty = COALESCE(:medicineStockQty, medicineStockQty),
               medicineUnitPrice = COALESCE(:medicineUnitPrice, medicineUnitPrice),
               medicineReorderLevel = COALESCE(:medicineReorderLevel, medicineReorderLevel),
               medicineUpdatedAt = GETDATE()
            WHERE
               medicineId = :medicineId
               AND medicineStatus <> 'DELETED'
            """;

    public static final String UPDATE_MEDICINE_QUANTITY = """
            UPDATE GROUP1_MEDICINE
            SET medicineStockQty = medicineStockQty + :medicineStockQty
            WHERE
                medicineId = :medicineId
                AND medicineHospitalId = :medicineHospitalId
                AND medicineStatus <> 'DELETED'
            """;

    public static final String GET_MEDICINE_BY_ID = """
            SELECT
                    h.hospitalName, m.medicineId, m.medicineName, m.medicineDescription,
                    m.medicineDosageForm, m.medicineDosage,  m.medicineExpiryDate,
                    m.medicineStockQty, m.medicineUnitPrice, m.medicineReorderLevel
            FROM GROUP1_MEDICINE m
            JOIN
                GROUP1_HOSPITAL h ON m.medicineHospitalId = h.hospitalId
            WHERE
                m.medicineId = :medicineId
                AND m.medicineHospitalId = :medicineHospitalId
                AND m.medicineStatus <> 'DELETED'
            """;

    public static final String GET_MEDICINE_BY_HOSPITAL_ID = """
            SELECT
                h.hospitalName,
                m.medicineId,
                m.medicineName,
                m.medicineDescription,
                m.medicineDosageForm,
                m.medicineDosage,
                m.medicineExpiryDate,
                m.medicineStockQty,
                m.medicineUnitPrice,
                m.medicineReorderLevel
            FROM
                GROUP1_MEDICINE m
            JOIN
                GROUP1_HOSPITAL h ON m.medicineHospitalId = h.hospitalId
            WHERE
                m.medicineHospitalId = :hospitalId
                AND m.medicineStatus <> 'DELETED'
            """;

    public static final String GET_MEDICINE_WITH_LOW_STOCK = """
            SELECT
                h.hospitalName,
                m.medicineId,
                m.medicineName,
                m.medicineDescription,
                m.medicineDosageForm,
                m.medicineDosage,
                m.medicineExpiryDate,
                m.medicineStockQty,
                m.medicineUnitPrice,
                m.medicineReorderLevel
            FROM
                GROUP1_MEDICINE m
            JOIN
                GROUP1_HOSPITAL h ON m.medicineHospitalId = h.hospitalId
            WHERE
                m.medicineHospitalId = :hospitalId
                AND m.medicineStockQty < m.medicineReorderLevel
                AND m.medicineStatus <> 'DELETED'
            """;

    public static final String GET_MEDICINE_BY_NAME = """
            SELECT
                h.hospitalName,
                m.medicineId,
                m.medicineName,
                m.medicineDescription,
                m.medicineDosageForm,
                m.medicineDosage,
                m.medicineExpiryDate,
                m.medicineStockQty,
                m.medicineUnitPrice,
                m.medicineReorderLevel
            FROM
                GROUP1_MEDICINE m
            JOIN
                GROUP1_HOSPITAL h ON m.medicineHospitalId = h.hospitalId
            WHERE
                LOWER(m.medicineName) LIKE LOWER(:medicineName)
                AND m.medicineHospitalId = :hospitalId
                AND m.medicineStatus <> 'DELETED'
            """;

    public static final String DELETE_MEDICINE_BY_ID = """
            UPDATE GROUP1_MEDICINE
            SET medicineStatus = 'DELETED',
                medicineUpdatedAt = GETDATE()
            WHERE
                medicineId = :medicineId
                AND medicineHospitalId = :hospitalId
                AND medicineStatus <> 'DELETED'
            """;
}
