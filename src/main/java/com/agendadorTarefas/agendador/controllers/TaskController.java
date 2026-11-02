package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.entities.Task;
import com.agendadorTarefas.agendador.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<Task> findById(Long id){

        Task task = taskService.findById(id);

        return ResponseEntity.ok().body(task);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> findAll(){
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> insert(Task task){

        Task newTask = taskService.create(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTask.getId()).toUri();

        return ResponseEntity.created(uri).body(newTask);
    }
}
