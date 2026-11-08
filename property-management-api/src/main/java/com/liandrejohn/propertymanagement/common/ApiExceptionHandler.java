package com.liandrejohn.propertymanagement.common;

import com.liandrejohn.propertymanagement.property.PropertyNotFoundException;
import com.liandrejohn.propertymanagement.property.PropertyPaymentValidationException;
import com.liandrejohn.propertymanagement.workspace.WorkspaceAccessDeniedException;
import com.liandrejohn.propertymanagement.workspace.WorkspaceNotConfiguredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(WorkspaceAccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleStatus(
            WorkspaceAccessDeniedException exception) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                exception.getMessage());

        return ResponseEntity
                .status(status)
                .body(problem);
    }

    @ExceptionHandler(WorkspaceNotConfiguredException.class)
    public ResponseEntity<ProblemDetail> handleStatus(
            WorkspaceNotConfiguredException exception) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                exception.getMessage());

        return ResponseEntity
                .status(status)
                .body(problem);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleStatus(
            PropertyNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                exception.getMessage());

        return ResponseEntity
                .status(status)
                .body(problem);
    }

    @ExceptionHandler(PropertyPaymentValidationException.class)
    public ResponseEntity<ProblemDetail> handleStatus(
            PropertyPaymentValidationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                status,
                exception.getMessage());

        return ResponseEntity
                .status(status)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.putIfAbsent(
                        error.getField(),
                        error.getDefaultMessage()
                ));

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Please check the submitted fields"
        );

        problem.setProperty("errors", errors);

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleUnreadableBody(
            HttpMessageNotReadableException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON or field value"
        );

        return ResponseEntity.badRequest().body(problem);
    }
}