package com.agendadorTarefas.agendador.dtos;

import java.time.LocalDateTime;

public record AppointmentRequest(
        String description,
        LocalDateTime moment,
        Integer status,
        Long taskId,
        Long clientId,
        Long workerId
) {
}
