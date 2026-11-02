package com.agendadorTarefas.agendador.exception;

public class NotFoundAppointmentException extends RuntimeException {
    public NotFoundAppointmentException(String message) {
        super(message);
    }
}
