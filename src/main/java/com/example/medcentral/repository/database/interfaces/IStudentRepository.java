package com.example.medcentral.repository.database.interfaces;

//import org.example.tesajbdemo.model.entity.Student;

import com.example.medcentral.model.entity.Student;
import com.example.medcentral.model.request.student.StudentQueryParams;
import com.example.medcentral.model.response.StudentResponse;

import java.util.List;

public interface IStudentRepository {
    long createStudent(Student student);

    List<Student> getAllStudents(StudentQueryParams queryParams);

    long updateStudent(Student student);

    Student getStudentById(long studentId);

    List<Student> getStudentsByFirstName(String studentFirstName);

    long deleteStudentById(long studentId);

    List<Student> searchStudents(String searchTerm);
}
