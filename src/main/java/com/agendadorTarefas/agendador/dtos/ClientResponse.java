package com.agendadorTarefas.agendador.dtos;

import java.util.Set;

public record ClientResponse(
        Long id,
        String name,
        String surname,
        String cellphone,
        String address,
        Set<AppointmentResponse> appointments
) {
}
