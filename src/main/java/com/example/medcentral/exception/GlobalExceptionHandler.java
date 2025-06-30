package com.example.medcentral.exception;//package com.example.tesajbdemo.exception;
////
//import com.example.tesajbdemo.model.response.BaseResponse;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
////
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<BaseResponse<Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
//        BaseResponse<Object> response = new BaseResponse.Builder<>()
//                .responseCode(HttpStatus.CONFLICT.value())  // 409
//                .responseCodeDescription("Conflict")
//                .responseMessage("Data integrity violation (e.g., duplicate key)")
//                .error(Map.of("error", "Record violates unique constraint or database rule"))
//                .build();
//
//        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGenericException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Something went wrong: " + ex.getMessage());
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
////
////
//////@Data
//////@Builder
//////@NoArgsConstructor
//////@AllArgsConstructor
////@ControllerAdvice
////public class GlobalExceptionHandler {
////
////    @ExceptionHandler(ResourceNotFoundException.class)
////    public ResponseEntity<BaseResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
////        BaseResponse<Object> response = new BaseResponse.Builder<>()
////                .responseCode(HttpStatus.NOT_FOUND.value())
////                .responseCodeDescription("Not Found")
////                .responseMessage(ex.getMessage())
////                .error(Map.of("error", ex.getMessage()))
////                .build();
////
////        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
////    }
////
////    @ExceptionHandler(DataIntegrityViolationException.class)
////    public ResponseEntity<BaseResponse<Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
////        BaseResponse<Object> response = new BaseResponse.Builder<>()
////                .responseCode(HttpStatus.CONFLICT.value())  // 409
////                .responseCodeDescription("Conflict")
////                .responseMessage("Data integrity violation (e.g., duplicate key)")
////                .error(Map.of("error", "Record violates unique constraint or database rule"))
////                .build();
////
////        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
////    }
////
////    @ExceptionHandler(MethodArgumentNotValidException.class)
////    public ResponseEntity<BaseResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
////        Map<String, String> errors = new HashMap<>();
////        ex.getBindingResult().getAllErrors().forEach(error -> {
////            String fieldName = ((FieldError) error).getField();
////            String errorMessage = error.getDefaultMessage();
////            errors.put(fieldName, errorMessage);
////        });
////
////        BaseResponse<Object> response = new BaseResponse.Builder<>()
////                .responseCode(HttpStatus.BAD_REQUEST.value())
////                .responseCodeDescription("Validation Failed")
////                .responseMessage("One or more validation errors occurred")
////                .error(errors)
////                .build();
////
////        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
////    }
////
////    // Add more @ExceptionHandler methods here for other exception types if needed
////    // fallback
////    @ExceptionHandler(Exception.class)
////    public ResponseEntity<BaseResponse<Object>> handleGenericException(Exception ex) {
////        BaseResponse<Object> response = new BaseResponse.Builder<>()
////                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
////                .responseCodeDescription("Internal Server Error")
////                .responseMessage("Something went wrong")
////                .error(Map.of("error", ex.getMessage()))
////                .build();
////
////        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////    }
////}
////
