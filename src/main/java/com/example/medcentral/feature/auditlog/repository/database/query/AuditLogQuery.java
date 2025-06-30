package com.example.medcentral.feature.auditlog.repository.database.query;

public class AuditLogQuery {

    public static final String CREATE_AUDIT_LOG = """
        INSERT INTO GROUP1_AUDIT_LOG 
            (httpMethod, description, tableName, recordId, createdBy, oldValue, newValue, source, query, ipAddress, userAgent)
        VALUES 
            (:httpMethod, :description, :tableName, :recordId, :createdBy, :oldValue, :newValue, :source, :query, :ipAddress, :userAgent)
    """;


    public static final String GET_AUDIT_LOGS = """
        SELECT * FROM GROUP1_AUDIT_LOG
        WHERE status != 'deleted'
    """;


    public static final String GET_AUDIT_LOG_BY_ID = """
        SELECT * FROM GROUP1_AUDIT_LOG
        WHERE auditLogId = :auditLogId
        AND status != 'deleted'
    """;

}


