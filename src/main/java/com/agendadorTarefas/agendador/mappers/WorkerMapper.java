package com.agendadorTarefas.agendador.mappers;

import com.agendadorTarefas.agendador.dtos.WorkerSummary;
import com.agendadorTarefas.agendador.entities.Worker;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {

    public Worker toEntity(WorkerSummary dto){
        Worker worker = new Worker();

        worker.setId(null);
        worker.setName(dto.name());
        worker.setSurname(dto.surname());
        return worker;
    }

    public WorkerSummary toDto(Worker worker){
        return new WorkerSummary(worker.getId(), worker.getName(),worker.getSurname());
    }
}
