package com.agendadorTarefas.agendador.mappers;

import com.agendadorTarefas.agendador.dtos.ClientSummary;
import com.agendadorTarefas.agendador.entities.Client;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ClientMapper {

    public Client toEntity(ClientSummary dto) {
        Client client = new Client();
        client.setId(null);
        client.setName(dto.name());
        client.setSurname(dto.surname());
        client.setAddress(dto.address());
        client.setCellPhone(dto.cellphone());

        return client;
    }

    public ClientSummary toDto(Client entity) {
        return new ClientSummary(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getAddress(),
                entity.getCellPhone()
        );
    }
}
