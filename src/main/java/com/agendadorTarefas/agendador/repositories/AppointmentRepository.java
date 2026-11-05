package com.agendadorTarefas.agendador.repositories;

import com.agendadorTarefas.agendador.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.worker.id = :workerId AND a.moment = :moment")
    Optional<Appointment> findByWorkerAndMoment(@Param("workerId") Long workerId, @Param("moment") LocalDateTime moment);
}
