package com.agendadorTarefas.agendador.repositories;

import com.agendadorTarefas.agendador.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
