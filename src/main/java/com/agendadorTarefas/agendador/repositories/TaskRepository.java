package com.agendadorTarefas.agendador.repositories;

import com.agendadorTarefas.agendador.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
