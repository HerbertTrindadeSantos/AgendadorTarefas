package com.agendadorTarefas.agendador.dtos;

public record ClientSummary(
        Long id,
        String name,
        String surname,
        String cellphone,
        String address
) {
}
