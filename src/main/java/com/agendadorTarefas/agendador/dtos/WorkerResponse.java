package com.agendadorTarefas.agendador.dtos;

import java.util.Set;

public record WorkerResponse(
        Long id,
        String name,
        String surname,
        Set<AppointmentResponse> appointments
) {
}
