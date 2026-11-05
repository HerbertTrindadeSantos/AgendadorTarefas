package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.entities.Client;
import com.agendadorTarefas.agendador.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){

        Client client = clientService.findById(id);

        return ResponseEntity.ok().body(client);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> findAll(){
        List<Client> clients = clientService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    public ResponseEntity<Client> insert(@RequestBody Client client){

        Client newClient = clientService.create(client);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClient.getId()).toUri();

        return ResponseEntity.created(uri).body(newClient);
    }
}
