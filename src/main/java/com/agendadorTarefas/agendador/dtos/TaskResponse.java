package com.agendadorTarefas.agendador.dtos;

import java.util.Set;

public record TaskResponse(
        Long id,
        String description,
        Set<AppointmentResponse> appointments
) {
}
