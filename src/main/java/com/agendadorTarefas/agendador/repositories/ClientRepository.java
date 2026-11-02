package com.agendadorTarefas.agendador.repositories;

import com.agendadorTarefas.agendador.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
