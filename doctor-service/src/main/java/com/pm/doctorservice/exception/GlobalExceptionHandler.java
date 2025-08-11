package com.pm.doctorservice.exception;

import com.pm.doctorservice.dto.StandardErrorResponse;
import com.pm.doctorservice.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //Map<String, String>
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
       /* Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );*/

        List<ValidationError> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());


        StandardErrorResponse standardErrorResponse = new StandardErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),"Validation failed for the request.",validationErrors);

        return ResponseEntity.badRequest().body(standardErrorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        log.error("Invalid argument exception: {}", ex.getMessage(), ex);

        StandardErrorResponse errorResponse = new StandardErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage()
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PhoneAlreadyExistException.class)
    public ResponseEntity<StandardErrorResponse> handleEmailAlreadyExistsException(
            PhoneAlreadyExistException ex
    ) {
        log.warn("Mobile already exists: {}", ex.getMessage());

        ValidationError validationError = new ValidationError(ex.getField(), ex.getMessage());

        StandardErrorResponse errorResponse = new StandardErrorResponse(
                HttpStatus.CONFLICT.value(), // Use 409 Conflict for resource conflicts
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                Collections.singletonList(validationError)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailPhoneAlreadyExistsException.class)
    public ResponseEntity<StandardErrorResponse> handleEmailPhoneAlreadyExistsException(
            EmailPhoneAlreadyExistsException ex
    ) {
        log.warn("Email already exists: {}", ex.getMessage());

        ValidationError validationError = new ValidationError(ex.getField(), ex.getMessage());

        StandardErrorResponse errorResponse = new StandardErrorResponse(
                HttpStatus.CONFLICT.value(), // Use 409 Conflict for resource conflicts
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                Collections.singletonList(validationError)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
   /* @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistException ex) {
        log.warn("Email already exists: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailPhoneAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailPhoneAlreadyExistsException(EmailPhoneAlreadyExistsException ex) {
        log.warn("Mobile already exists: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }*/
}
