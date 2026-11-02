package com.agendadorTarefas.agendador.exception;

public class NotFoundClientException extends RuntimeException {
    public NotFoundClientException(String message) {
        super(message);
    }
}
