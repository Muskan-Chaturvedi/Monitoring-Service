package com.training.monitor.exception;

import com.training.monitor.dto.ErrorResponseObject;
import com.training.monitor.dto.RootResponseDTO;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RootResponseDTO> resourceNotFoundException(ResourceNotFoundException ex) {

        List<ErrorResponseObject> errorResponseObjectList=new ArrayList<ErrorResponseObject>();

        ErrorResponseObject errorResponseObject= new ErrorResponseObject("NOT_FOUND",ex.getMessage());
        errorResponseObjectList.add(errorResponseObject);

        return ResponseEntity.ok(new RootResponseDTO(false,"",true,errorResponseObjectList));
    }

    @ExceptionHandler(MethodArgumentNotValid.class)
    public ResponseEntity<RootResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValid ex) {

        List<ErrorResponseObject> errorResponseObjectList=new ArrayList<ErrorResponseObject>();

        ErrorResponseObject errorResponseObject=new ErrorResponseObject("MethodArgumentNotValid",ex.getMessage());
        errorResponseObjectList.add(errorResponseObject);

        return ResponseEntity.ok(new RootResponseDTO(false,"Validation Failed",true,errorResponseObjectList));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RootResponseDTO> globalExceptionHandler(Exception ex) {

        List<ErrorResponseObject> errorResponseObjectList=new ArrayList<ErrorResponseObject>();

        ErrorResponseObject errorResponseObject=new ErrorResponseObject("INTERNAL SERVER ERROR",ex.getMessage());
        errorResponseObjectList.add(errorResponseObject);

        return ResponseEntity.ok(new RootResponseDTO(false,"",true,errorResponseObjectList));
    }
}
