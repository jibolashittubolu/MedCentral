package com.example.medcentral.feature.equipment.repository.database.query;

public class EquipmentQuery {

    public static final String CREATE_EQUIPMENT = """
        INSERT INTO GROUP1_EQUIPMENT
            (equipmentHospitalId, equipmentName, equipmentDescription, equipmentType, equipmentPurchaseDate, equipmentExpiryDate, equipmentLastMaintenanceDate, equipmentNextMaintenanceDate )
        VALUES
            (:equipmentHospitalId, :equipmentName, :equipmentDescription, :equipmentType, :equipmentPurchaseDate, :equipmentExpiryDate, :equipmentLastMaintenanceDate, :equipmentNextMaintenanceDate )
    """;


    public static final String GET_EQUIPMENTS = """
        SELECT * FROM GROUP1_EQUIPMENT
        WHERE equipmentCondition != 'deleted'
    """;


    public static final String GET_EQUIPMENT_BY_ID = """
        SELECT * FROM GROUP1_EQUIPMENT
        WHERE equipmentId = :equipmentId
        AND equipmentCondition != 'deleted'
    """;

    public static final String UPDATE_EQUIPMENT = """
        UPDATE GROUP1_EQUIPMENT
                            SET
                                equipmentName = COALESCE(:equipmentName, equipmentName),
                                equipmentDescription = COALESCE(:equipmentDescription, equipmentDescription),
                                equipmentType = COALESCE(:equipmentType, equipmentType),
                                equipmentPurchaseDate = COALESCE(:equipmentPurchaseDate, equipmentPurchaseDate),
                                equipmentExpiryDate = COALESCE(:equipmentExpiryDate, equipmentExpiryDate),
                                equipmentLastMaintenanceDate = COALESCE(:equipmentLastMaintenanceDate, equipmentLastMaintenanceDate),
                                equipmentNextMaintenanceDate = COALESCE(:equipmentNextMaintenanceDate, equipmentNextMaintenanceDate),
                                equipmentUpdatedAt = CASE
                                    WHEN
                                        (
                                        COALESCE(:equipmentName, equipmentName) <> equipmentName OR
                                        COALESCE(:equipmentDescription, equipmentDescription) <> equipmentDescription OR
                                        COALESCE(:equipmentType, equipmentType) <> equipmentType OR
                                        COALESCE(:equipmentPurchaseDate, equipmentPurchaseDate) <> equipmentPurchaseDate OR
                                        COALESCE(:equipmentExpiryDate, equipmentExpiryDate) <> equipmentExpiryDate OR
                                        COALESCE(:equipmentLastMaintenanceDate, equipmentLastMaintenanceDate)<> equipmentLastMaintenanceDate OR
                                        COALESCE(:equipmentNextMaintenanceDate, equipmentExpiryDate) <> equipmentExpiryDate
                                        )
                                    THEN GETDATE()
                                    ELSE equipmentUpdatedAt
                                END
                        WHERE equipmentId = :equipmentId
                                  AND equipmentCondition != 'DELETED';
    """;

    public static final String DELETE_EQUIPMENT = """
        UPDATE GROUP1_EQUIPMENT
           SET equipmentCondition = 'DELETED'
         WHERE equipmentId = :equipmentId
         AND equipmentCondition != 'DELETED';
    """;
}


