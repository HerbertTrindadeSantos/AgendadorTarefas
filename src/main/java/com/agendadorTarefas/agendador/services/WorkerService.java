package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.entities.Worker;
import com.agendadorTarefas.agendador.exception.NotFoundWorkerException;
import com.agendadorTarefas.agendador.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    public Worker findById(Long id){
        return workerRepository.findById(id).orElseThrow(()->new NotFoundWorkerException("Not Found Worker id: " + id));
    }

    public List<Worker> findAll(){
        return workerRepository.findAll();
    }

    public Worker create(Worker worker){
        return workerRepository.save(worker);
    }
}

