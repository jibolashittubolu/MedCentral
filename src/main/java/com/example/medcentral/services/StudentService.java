package com.example.medcentral.services;

import com.example.medcentral.exception.ResourceNotFoundException;
import com.example.medcentral.mapper.IStudentMapper;
import com.example.medcentral.model.entity.Student;
import com.example.medcentral.model.request.student.StudentCreateRequest;
import com.example.medcentral.model.request.student.StudentQueryParams;
import com.example.medcentral.model.request.student.StudentUpdateRequest;
import com.example.medcentral.model.response.StudentResponse;
import com.example.medcentral.repository.database.interfaces.IStudentRepository;
import com.example.medcentral.repository.database.interfaces.IStudentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;
    private final IStudentMapper studentMapper;

    @Autowired
    public StudentService(
            IStudentRepository studentRepository,
            IStudentMapper studentMapper
    ) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public long createStudent(StudentCreateRequest request) {
        try {
            Gson gson = new Gson();
//        var myrequestbody = gson.toJson(request);
            //Gson is a package to help deal with JSON -> Convert to and from json
            var student = gson.fromJson(gson.toJson(request), Student.class);
//        System.out.println("STUDENT NAME >>");
//        System.out.println(student.getStudentFirstName());
            return studentRepository.createStudent(student);
        }
        catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<StudentResponse> getAllStudents(StudentQueryParams queryParams) {
        try{
            return studentRepository
                    .getAllStudents(queryParams)
                    .stream()
                    .map(studentMapper::toResponse)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public long updateStudent(StudentUpdateRequest request) {
        try {

            // Optionally, verify student exists before updating
            // For example, you can have getStudentById and throw ResourceNotFoundException if not found

            Gson gson = new Gson();
            // Convert request to entity (you can map manually or use Gson)
            Student student = gson.fromJson(gson.toJson(request), Student.class);

             long rowsAffected = studentRepository.updateStudent(student);

            if (rowsAffected == 0) {
               throw new Exception("Student not found");
//                throw new ResourceNotFoundException("Student with ID " + request.getStudentId() + " not found");
            }

            return request.getStudentId();
        }
        catch (Exception e) {
//            throw e;
            throw new RuntimeException(e);
        }


    }


    @Override
    public Student getStudentById(long studentId) {
        Student student = studentRepository.getStudentById(studentId);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        return student;
    }

    @Override
    public List<Student> getStudentsByFirstName(String studentFirstName) {
        List<Student> students = studentRepository.getStudentsByFirstName(studentFirstName);
        if (students == null || students.isEmpty()) {
            throw new ResourceNotFoundException("No students found with first name: " + studentFirstName);
        }
        return students;
    }

    @Override
    public void deleteStudent(long studentId) {
        long affectedRows = studentRepository.deleteStudentById(studentId);
        if (affectedRows == 0) {
            throw new ResourceNotFoundException("Student with ID " + studentId + " not found.");
        }
    }

    @Override
    public List<Student> searchStudents(String searchTerm) {
        return studentRepository.searchStudents(searchTerm);
    }

}
