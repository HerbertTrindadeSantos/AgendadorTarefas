package com.agendadorTarefas.agendador.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record AppointmentRequest(
        @NotBlank(message = "Description is required")
        @Size(max = 150,message = "Description too long ")
        String description,

        @NotNull(message = "Moment is requered")
        @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
        LocalDateTime moment,

        @Min(value = 1, message = "Status must be between 1 and 3")
        @Max(value = 3, message = "Status must be between 1 and 3")
        Integer status,

        @Positive
        @NotNull(message = "Task is requered")
        Long taskId,

        @Positive
        @NotNull(message = "client is requered")
        Long clientId,

        @Positive
        @NotNull(message = "worker is requered")
        Long workerId
) {
}
