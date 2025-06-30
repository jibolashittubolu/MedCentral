package com.example.medcentral.mapper;


import com.example.medcentral.model.entity.Student;
import com.example.medcentral.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

//    @Mapping(target = "studentCreatedAt", source = "studentCreatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
//    @Mapping(target = "studentUpdatedAt", source = "studentUpdatedAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    StudentResponse toResponse(Student student);
//    Student toEntity(StudentCreateRequest request);
}
