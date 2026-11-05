package com.agendadorTarefas.agendador.controllers;

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
    public ResponseEntity<Worker> findById(@PathVariable Long id){

        Worker worker = workerService.findById(id);

        return ResponseEntity.ok().body(worker);
    }

    @GetMapping()
    public ResponseEntity<List<Worker>> findAll(){
        List<Worker> workers = workerService.findAll();
        return ResponseEntity.ok().body(workers);
    }

    @PostMapping
    public ResponseEntity<Worker> insert(@RequestBody Worker worker){

        Worker newWorker = workerService.create(worker);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newWorker.getId()).toUri();

        return ResponseEntity.created(uri).body(newWorker);
    }
}
