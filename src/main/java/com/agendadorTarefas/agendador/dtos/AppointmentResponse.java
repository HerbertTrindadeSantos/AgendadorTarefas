package com.agendadorTarefas.agendador.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        String description,
        @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
        LocalDateTime moment,
        String status,
        TaskSummary task,
        ClientSummary client,
        WorkerSummary worker

        ) {

}
