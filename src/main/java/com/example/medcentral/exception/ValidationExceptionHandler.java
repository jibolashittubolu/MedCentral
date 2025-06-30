package com.example.medcentral.exception;//package com.example.tesajbdemo.exception;
//
//import com.example.tesajbdemo.model.response.BaseResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice(assignableTypes = {com.example.tesajbdemo.controller.StudentController.class})
//public class ValidationExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<BaseResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        BaseResponse<Object> response = new BaseResponse.Builder<>()
//                .responseCode(HttpStatus.BAD_REQUEST.value())
//                .responseCodeDescription("Validation Failed")
//                .responseMessage("Validation errors occurred")
//                .error(errors)
//                .build();
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//}
