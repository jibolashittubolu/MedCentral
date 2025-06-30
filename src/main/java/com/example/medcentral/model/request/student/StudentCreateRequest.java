package com.example.medcentral.model.request.student;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class StudentCreateRequest {

    @NotBlank(message = "First name is required")
    private String studentFirstName;

    @NotBlank(message = "Last name is required")
    private String studentLastName;

    @Min(value = 18, message = "Age must be positive")
    private int studentAge;

    @NotBlank(message = "Matric number is required")
    private String studentMatricNumber;

    @NotBlank(message = "State of origin is required")
    private String studentStateOfOrigin;

//    // Constructors
//    public StudentCreateRequest() {}
//
//    // Getters and setters
//    public String getStudentFirstName() {
//        return studentFirstName;
//    }
//    public void setStudentFirstName(String studentFirstName) {
//        this.studentFirstName = studentFirstName;
//    }
//
//    public String getStudentLastName() {
//        return studentLastName;
//    }
//    public void setStudentLastName(String studentLastName) {
//        this.studentLastName = studentLastName;
//    }
//
//    public int getStudentAge() {
//        return studentAge;
//    }
//    public void setStudentAge(int studentAge) {
//        this.studentAge = studentAge;
//    }
//
//    public String getStudentMatricNumber() {
//        return studentMatricNumber;
//    }
//    public void setStudentMatricNumber(String studentMatricNumber) {
//        this.studentMatricNumber = studentMatricNumber;
//    }
//
//    public String getStudentStateOfOrigin() {
//        return studentStateOfOrigin;
//    }
//    public void setStudentStateOfOrigin(String studentStateOfOrigin) {
//        this.studentStateOfOrigin = studentStateOfOrigin;
//    }
}
