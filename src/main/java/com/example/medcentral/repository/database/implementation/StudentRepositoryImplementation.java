package com.example.medcentral.repository.database.implementation;

import com.example.medcentral.model.entity.Student;
import com.example.medcentral.model.request.student.StudentQueryParams;
import com.example.medcentral.model.response.StudentResponse;
import com.example.medcentral.repository.database.interfaces.IStudentRepository;
import com.example.medcentral.repository.database.query.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class StudentRepositoryImplementation implements IStudentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepositoryImplementation(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createStudent(Student student) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentFirstName", student.getStudentFirstName())
                .addValue("studentLastName", student.getStudentLastName())
                .addValue("studentAge", student.getStudentAge())
                .addValue("studentMatricNumber", student.getStudentMatricNumber())
                .addValue("studentStateOfOrigin", student.getStudentStateOfOrigin());

        return jdbcTemplate.update(
                StudentQuery.INSERT_STUDENT,
                parameters
        );
    }

    @Override
    public List<Student> getAllStudents(StudentQueryParams params) {
        StringBuilder queryBuilder = new StringBuilder(StudentQuery.GET_STUDENTS);
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();

        BeanPropertyRowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);



        if (params.getStateOfOrigin() != null) {
            queryBuilder.append(" AND studentStateOfOrigin = :stateOfOrigin");
            sqlParams.addValue("stateOfOrigin", params.getStateOfOrigin());
        }

        if (params.getStudentFirstName() != null) {
            queryBuilder.append(" AND studentFirstName = :studentFirstName");
            sqlParams.addValue("studentFirstName", params.getStudentFirstName());
        }

        if (params.getStudentLastName() != null) {
            queryBuilder.append(" AND studentLastName = :studentLastName");
            sqlParams.addValue("studentLastName", params.getStudentLastName());
        }

        if (params.getAge() != null) {
            queryBuilder.append(" AND studentAge = :age");
            sqlParams.addValue("age", params.getAge());
        }

        return jdbcTemplate.query( queryBuilder.toString() ,rowMapper);

//        return jdbcTemplate.query(queryBuilder.toString(), sqlParams, (rs, rowNum) -> {
//            Student student = new Student();
//            student.setStudentId(rs.getInt("studentId"));
//            student.setStudentFirstName(rs.getString("studentFirstName"));
//            student.setStudentLastName(rs.getString("studentLastName"));
//            student.setStudentAge(rs.getInt("studentAge"));
//            student.setStudentMatricNumber(rs.getString("studentMatricNumber"));
//            student.setStudentStateOfOrigin(rs.getString("studentStateOfOrigin"));
////            student.setStudentStateOfOrigin(rs.getString("studentStateOfOrigin"));
//            return student;
//        });
    }


    @Override
    public long updateStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentId", student.getStudentId())
                .addValue("studentFirstName", student.getStudentFirstName())
                .addValue("studentLastName", student.getStudentLastName())
                .addValue("studentAge", student.getStudentAge())
                .addValue("studentMatricNumber", student.getStudentMatricNumber())
                .addValue("studentStateOfOrigin", student.getStudentStateOfOrigin());

        return jdbcTemplate.update(StudentQuery.UPDATE_STUDENT, params);
    }


    @Override
    public Student getStudentById(long studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);

        try {
            return jdbcTemplate.queryForObject(StudentQuery.GET_STUDENT_BY_ID, params, (rs, rowNum) -> {
                Student student = new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setStudentFirstName(rs.getString("studentFirstName"));
                student.setStudentLastName(rs.getString("lastName"));
                student.setStudentAge(rs.getInt("age"));
                student.setStudentMatricNumber(rs.getString("studentMatricNumber"));
                student.setStudentStateOfOrigin(rs.getString("stateOfOrigin"));
                student.setStudentUpdatedAt(Timestamp.valueOf(rs.getTimestamp("studentUpdatedAt").toLocalDateTime()));
                return student;
            });
        }
        catch (EmptyResultDataAccessException e) {
            return null;  // or throw a custom ResourceNotFoundException if preferred
        }
    }

    @Override
    public List<Student> getStudentsByFirstName(String studentFirstName) {
        MapSqlParameterSource params = new MapSqlParameterSource("studentFirstName", studentFirstName);

        return jdbcTemplate.query(StudentQuery.GET_STUDENTS_BY_FIRST_NAME, params, (rs, rowNum) -> {
            Student student = new Student();
            student.setStudentId(rs.getInt("studentId"));
            student.setStudentFirstName(rs.getString("studentFirstName"));
            student.setStudentLastName(rs.getString("lastName"));
            student.setStudentAge(rs.getInt("age"));
            student.setStudentMatricNumber(rs.getString("studentMatricNumber"));
            student.setStudentStateOfOrigin(rs.getString("stateOfOrigin"));
            student.setStudentUpdatedAt(Timestamp.valueOf(rs.getTimestamp("studentUpdatedAt").toLocalDateTime()));
            return student;
        });
    }


    @Override
    public long deleteStudentById(long studentId) {
        MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
        return jdbcTemplate.update(StudentQuery.DELETE_STUDENT, params);
    }


    @Override
    public List<Student> searchStudents(String searchTerm) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("search", "%" + searchTerm + "%");

        return jdbcTemplate.query(
                StudentQuery.SEARCH_STUDENTS,
                params,
                new BeanPropertyRowMapper<>(Student.class)
        );
    }

}