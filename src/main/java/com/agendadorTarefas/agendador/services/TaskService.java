package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.entities.Task;
import com.agendadorTarefas.agendador.exception.NotFoundTaskException;
import com.agendadorTarefas.agendador.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(()->new NotFoundTaskException("Not Found Task id: " + id));
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task create(Task task){
        return taskRepository.save(task);
    }
}

