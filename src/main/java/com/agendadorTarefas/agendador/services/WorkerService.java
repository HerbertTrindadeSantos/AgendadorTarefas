package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.dtos.WorkerSummary;
import com.agendadorTarefas.agendador.entities.Worker;
import com.agendadorTarefas.agendador.exception.NotFoundWorkerException;
import com.agendadorTarefas.agendador.mappers.WorkerMapper;
import com.agendadorTarefas.agendador.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private WorkerRepository workerRepository;
    private WorkerMapper workerMapper;

    public WorkerService(WorkerRepository workerRepository, WorkerMapper workerMapper) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
    }

    public WorkerSummary findById(Long id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new NotFoundWorkerException("Not Found Worker id: " + id));
        return workerMapper.toDto(worker);
    }

    public List<WorkerSummary> findAll() {
        return workerRepository.findAll().stream()
                .map(workerMapper::toDto).toList();
    }

    public WorkerSummary create(WorkerSummary dto) {
        Worker worker = workerMapper.toEntity(dto);
        workerRepository.save(worker);
        return workerMapper.toDto(worker);
    }
}

