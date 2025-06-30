package com.example.medcentral.controller;

import com.example.medcentral.model.entity.Student;
import com.example.medcentral.model.request.student.StudentCreateRequest;
import com.example.medcentral.model.request.student.StudentQueryParams;
import com.example.medcentral.model.request.student.StudentUpdateRequest;
import com.example.medcentral.model.response.BaseResponse;
import com.example.medcentral.model.response.CustomResponseCode;
import com.example.medcentral.model.response.StudentResponse;
import com.example.medcentral.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Validated  // enable @Min on @RequestParam
@RestController
//@RequestMapping("/api/v1/students")
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/createStudent")
    public ResponseEntity<BaseResponse<Long>> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        long rowsAffected = studentService.createStudent(request);

        String message = "A student has been created successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.CREATED.getStatusCode())
                .responseCodeDescription(CustomResponseCode.CREATED.getStatusCodeDescription())
                .responseMessage(message)
//                .data((long) createdStudentId)
                .data(rowsAffected)
                .build();

//        return ResponseEntity.status(CustomResponseStatus.CREATED.getStatusCode()).body(response);
//        return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<BaseResponse<List<StudentResponse>>> getAllStudents(@Valid StudentQueryParams queryParams) {
        List<StudentResponse> students = studentService.getAllStudents(queryParams);

        BaseResponse<List<StudentResponse>> response = new BaseResponse.Builder<List<StudentResponse>>()
                .responseCode(HttpStatus.OK.value())
                .responseCodeDescription("Success")
                .responseMessage("Students retrieved successfully")
                .data(students)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/updateStudent")
    public ResponseEntity<BaseResponse<Long>> updateStudent(@Valid @RequestBody StudentUpdateRequest request) {
        long updatedStudentId = studentService.updateStudent(request);

        String message = "Student with ID " + updatedStudentId + " has been updated successfully";

        BaseResponse<Long> response = new BaseResponse.Builder<Long>()
                .responseCode(CustomResponseCode.OK.getStatusCode())
                .responseCodeDescription(CustomResponseCode.OK.getStatusCodeDescription())
                .responseMessage(message)
                .data(updatedStudentId)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<BaseResponse<Student>> getStudentById(@PathVariable long studentId) {
        Student student = studentService.getStudentById(studentId);

        BaseResponse<Student> response = new BaseResponse.Builder<Student>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Student found")
                .data(student)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/getStudentByFirstName")
    public ResponseEntity<BaseResponse<List<Student>>> getStudentsByFirstName(@RequestParam String firstName) {
        List<Student> students = studentService.getStudentsByFirstName(firstName);

        BaseResponse<List<Student>> response = new BaseResponse.Builder<List<Student>>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Students retrieved successfully")
                .data(students)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<BaseResponse<Object>> deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);

        BaseResponse<Object> response = new BaseResponse.Builder<>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Student deleted successfully")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<List<Student>>> searchStudents(@RequestParam String term) {
        List<Student> results = studentService.searchStudents(term);

        BaseResponse<List<Student>> response = new BaseResponse.Builder<List<Student>>()
                .responseCode(200)
                .responseCodeDescription("OK")
                .responseMessage("Search completed successfully")
                .data(results)
                .build();

        return ResponseEntity.ok(response);
    }

}
