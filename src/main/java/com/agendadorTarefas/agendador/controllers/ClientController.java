package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.dtos.ClientSummary;
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
    public ResponseEntity<ClientSummary> findById(@PathVariable Long id){

        ClientSummary client = clientService.findById(id);

        return ResponseEntity.ok().body(client);
    }

    @GetMapping()
    public ResponseEntity<List<ClientSummary>> findAll(){
        List<ClientSummary> clients = clientService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    public ResponseEntity<ClientSummary> insert(@RequestBody ClientSummary dto){

        ClientSummary client = clientService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.id()).toUri();

        return ResponseEntity.created(uri).body(client);
    }
}
