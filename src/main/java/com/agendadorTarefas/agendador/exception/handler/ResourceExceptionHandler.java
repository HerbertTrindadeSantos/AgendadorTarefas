package com.agendadorTarefas.agendador.exception.handler;


import com.agendadorTarefas.agendador.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundWorkerException.class)
    public ResponseEntity<StandError> notFoundWorkerException(NotFoundWorkerException e, HttpServletRequest request){

        String error = "Worker not found";

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandError standError = new StandError(Instant.now(),status.value(),
                error,e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standError);
    }

    @ExceptionHandler(NotFoundTaskException.class)
    public ResponseEntity<StandError> notFoundTaskException(NotFoundTaskException e, HttpServletRequest request){

        String error = "Task not found";

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandError standError = new StandError(Instant.now(),status.value(),
                error,e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standError);
    }

    @ExceptionHandler(NotFoundAppointmentException.class)
    public ResponseEntity<StandError> notFoundAppointmentException(NotFoundAppointmentException e, HttpServletRequest request){

        String error = "Appointment not found";

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandError standError = new StandError(Instant.now(),status.value(),
                error,e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standError);
    }

    @ExceptionHandler(NotFoundClientException.class)
    public ResponseEntity<StandError> notFoundClientException(NotFoundClientException e, HttpServletRequest request){

        String error = "Client not found";

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandError standError = new StandError(Instant.now(),status.value(),
                error,e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standError);
    }

    @ExceptionHandler(DuplicateDateException.class)
    public ResponseEntity<StandError> duplicateDateException(DuplicateDateException e, HttpServletRequest request){

        String error = "Duplicate date";

        HttpStatus status = HttpStatus.CONFLICT;

        StandError standError = new StandError(Instant.now(),status.value(),
                error,e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standError);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<StandError> invalidDateException(InvalidDateException e, HttpServletRequest request){

        String error = "Date invalid";

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandError standError = new StandError(Instant.now(),status.value(),
                error,e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standError);

    }

}
