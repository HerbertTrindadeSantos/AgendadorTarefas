package com.agendadorTarefas.agendador.dtos;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        String description,
        LocalDateTime moment,
        String status,
        TaskSummary task,
        ClientSummary client,
        WorkerSummary worker

        ) {

}
