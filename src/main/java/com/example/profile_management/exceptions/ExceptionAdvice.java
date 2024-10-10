package com.example.profile_management.exceptions;

import com.example.profile_management.records.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle404(ResourceNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).reason(ex.getMessage()).build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handle500(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder()
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .reason(ex.getMessage())
                        .build()
        );
    }
    // catch all body,path variable and path params errors
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponse> handle400(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .reason(ex.getMessage())
                        .build()
        );
    }
}
