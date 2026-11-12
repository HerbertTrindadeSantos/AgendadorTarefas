package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.dtos.WorkerSummary;
import com.agendadorTarefas.agendador.entities.Worker;
import com.agendadorTarefas.agendador.services.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    private WorkerService workerService;

    public WorkerController(WorkerService workerService){
        this.workerService = workerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerSummary> findById(@PathVariable Long id){

        WorkerSummary worker = workerService.findById(id);

        return ResponseEntity.ok().body(worker);
    }

    @GetMapping()
    public ResponseEntity<List<WorkerSummary>> findAll(){
        List<WorkerSummary> workers = workerService.findAll();
        return ResponseEntity.ok().body(workers);
    }

    @PostMapping
    public ResponseEntity<WorkerSummary> insert(@RequestBody WorkerSummary dto){

        WorkerSummary worker = workerService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(worker.id()).toUri();

        return ResponseEntity.created(uri).body(worker);
    }
}
