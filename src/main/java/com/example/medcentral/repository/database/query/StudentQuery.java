package com.example.medcentral.repository.database.query;

public class StudentQuery {

    public static final String INSERT_STUDENT = """
        INSERT INTO TESA_JB_STUDENT 
            (studentFirstName, studentLastName,  studentAge,  studentMatricNumber, studentStateOfOrigin)
        VALUES 
            (:studentFirstName, :studentLastName, :studentAge, :studentMatricNumber, :studentStateOfOrigin)
    """;


    public static final String GET_STUDENTS = """
        SELECT * FROM TESA_JB_STUDENT
        WHERE deleted = 0
    """;
    public static final String ADMIN_GET_STUDENTS = """
        SELECT * FROM TESA_JB_STUDENT
    """;

    public static final String GET_STUDENT_BY_ID = """
        SELECT * FROM TESA_JB_STUDENT
        WHERE studentId = :studentId
        AND deleted = 0
    """;

    public static final String GET_STUDENT_BY_STUDENTMATRICNUMBER = """
        SELECT * FROM TESA_JB_STUDENT
        WHERE studentMatricNumber = :studentMatricNumber
        AND deleted = 0
    """;

    public static final String GET_STUDENTS_BY_FIRST_NAME = """
        SELECT * FROM TESA_JB_STUDENT
        WHERE studentFirstName = :studentFirstName
        AND deleted = 0
    """;

    public static final String UPDATE_STUDENT = """
        UPDATE TESA_JB_STUDENT
            SET
                studentFirstName = COALESCE(:studentFirstName, studentFirstName),
                studentLastName = COALESCE(:studentLastName, studentLastName),
                stateOfOrigin = COALESCE(:studentStateOfOrigin, stateOfOrigin),
                studentAge = COALESCE(:studentAge, studentAge),
                studentUpdatedAt = CASE
                    WHEN
                        ( COALESCE(:studentFirstName, studentFirstName) <> studentFirstName OR
                             COALESCE(:studentLastName, studentLastName) <> studentLastName OR
                             COALESCE(:studentStateOfOrigin, stateOfOrigin) <> studentStateOfOrigin OR
                             COALESCE(:studentAge, studentAge) <> studentAge )
                    THEN GETDATE()
                    ELSE studentUpdatedAt
                END
        WHERE studentId = :studentId
         AND deleted = 0;
    """;

    public static final String DELETE_STUDENT = """
        UPDATE TESA_JB_STUDENT
           SET studentStatus = 'deleted'
         WHERE studentId = :studentId
         AND deleted = 0;
    """;

    public static final String SEARCH_STUDENTS = """
    SELECT * FROM TESA_JB_STUDENT
    WHERE deleted = 0 AND (
        studentFirstName LIKE :search OR
        studentLastName LIKE :search OR
        studentMatricNumber LIKE :search OR
        studentStateOfOrigin LIKE :search OR
        CAST(studentAge AS VARCHAR) LIKE :search
    )
""";
}


