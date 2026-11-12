package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.dtos.TaskSummary;
import com.agendadorTarefas.agendador.entities.Task;
import com.agendadorTarefas.agendador.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSummary> findById(@PathVariable Long id){

        TaskSummary task = taskService.findById(id);

        return ResponseEntity.ok().body(task);
    }

    @GetMapping()
    public ResponseEntity<List<TaskSummary>> findAll(){
        List<TaskSummary> tasks = taskService.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskSummary> insert(@RequestBody TaskSummary dto){

        TaskSummary task = taskService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(task.id()).toUri();

        return ResponseEntity.created(uri).body(task);
    }
}
