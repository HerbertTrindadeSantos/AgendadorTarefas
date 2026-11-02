package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.entities.Client;
import com.agendadorTarefas.agendador.exception.NotFoundClientException;
import com.agendadorTarefas.agendador.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client findById(Long id){
        return clientRepository.findById(id).orElseThrow(()->new NotFoundClientException("Not Found Client id: " + id));
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client create(Client Client){
        return clientRepository.save(Client);
    }
}

