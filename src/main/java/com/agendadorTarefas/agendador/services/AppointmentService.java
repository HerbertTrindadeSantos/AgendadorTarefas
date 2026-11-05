package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.exception.DuplicateDateException;
import com.agendadorTarefas.agendador.exception.InvalidDateException;
import com.agendadorTarefas.agendador.exception.NotFoundAppointmentException;
import com.agendadorTarefas.agendador.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment findById(Long id){
        return appointmentRepository.findById(id).orElseThrow(()->new NotFoundAppointmentException("Not Found Appointment id: " + id));
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment create(Appointment appointment){
        this.isValid(appointment.getMoment());
        this.verification(appointment.getWorker().getId(), appointment.getMoment());
        return appointmentRepository.save(appointment);
    }

    private boolean isValid(LocalDateTime moment){
        if (moment == null){
            throw new InvalidDateException("Date cannot null");
        } else if (moment.isBefore(LocalDateTime.now())){
            throw new InvalidDateException("Date invalid");
        }
        return true;
    }

    private boolean verification(Long workerId, LocalDateTime moment){
        appointmentRepository.findByWorkerAndMoment(workerId, moment)
                .ifPresent(appointment -> {
                    throw new DuplicateDateException("Appointment already exists for this worker at this time.");
                });
        return true;
    }
}

