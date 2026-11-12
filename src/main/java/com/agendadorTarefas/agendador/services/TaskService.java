package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.dtos.TaskSummary;
import com.agendadorTarefas.agendador.entities.Task;
import com.agendadorTarefas.agendador.exception.NotFoundTaskException;
import com.agendadorTarefas.agendador.mappers.TaskMapper;
import com.agendadorTarefas.agendador.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskSummary findById(Long id) {


        Task task = taskRepository.findById(id).
                orElseThrow(() -> new NotFoundTaskException("Not Found Task id: " + id));

        return taskMapper.toDto(task);
    }

    public List<TaskSummary> findAll() {
        return taskRepository.findAll().stream().
                map(taskMapper::toDto).toList();
    }

    public TaskSummary create(TaskSummary dto) {
        Task task = taskMapper.toEntity(dto);
        taskRepository.save(task);

        return taskMapper.toDto(task);
    }
}

