package com.example.rate_limiting_demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.accept.InvalidApiVersionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
@NullMarked
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ProblemDetail> handleResourceExistsException(ResourceExistsException ex, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Field Validation Failed");
        problemDetail.setDetail(ex.getLocalizedMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNoFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setDetail(ex.getLocalizedMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidApiVersionException.class)
    public ResponseEntity<String> handleInvalidApiVersionException(InvalidApiVersionException ex){
        return ResponseEntity.badRequest().body("Invalid API version");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Illegal Argument");
        problemDetail.setDetail(ex.getLocalizedMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

}
