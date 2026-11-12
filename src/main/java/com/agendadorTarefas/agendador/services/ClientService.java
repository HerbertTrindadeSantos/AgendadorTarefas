package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.dtos.ClientSummary;
import com.agendadorTarefas.agendador.entities.Client;
import com.agendadorTarefas.agendador.exception.NotFoundClientException;
import com.agendadorTarefas.agendador.mappers.ClientMapper;
import com.agendadorTarefas.agendador.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientSummary findById(Long id) {
        Client client = clientRepository.findById(id).
                orElseThrow(() -> new NotFoundClientException("Not Found Client id: " + id));
        return clientMapper.toDto(client);
    }

    public List<ClientSummary> findAll() {
        return clientRepository.findAll().stream().
                map(clientMapper::toDto).toList();
    }

    public ClientSummary create(ClientSummary dto) {
        Client client = clientMapper.toEntity(dto);
        clientRepository.save(client);
        return clientMapper.toDto(client);

    }
}

