package com.promedicus.admissions.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.promedicus.admissions.controller.OperationResponse;

/**
 * Handler for applications exceptions
 * 
 * @author jjob
 */
@ControllerAdvice
public class AdmissionActionResponseExceptionHandler extends ResponseEntityExceptionHandler {


    private ResponseEntity<OperationResponse> error(Exception e, HttpStatus status) {
        OperationResponse resp = new OperationResponse(status.value(), e.getMessage(), null);
        return ResponseEntity.status(status).body(resp);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<OperationResponse> handleRunTimeException(Exception e,
                                                                    HttpStatus status) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AdmissionException.class)
    public ResponseEntity<OperationResponse> handleAdmissionException(Exception e,
                                                                      HttpServletResponse response) {
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<OperationResponse> handleCustomException(Exception e,
                                                                   HttpServletResponse response) {
        return error(e, HttpStatus.BAD_REQUEST);
    }
}
