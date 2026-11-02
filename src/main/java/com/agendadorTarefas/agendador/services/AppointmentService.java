package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.exception.NotFoundAppointmentException;
import com.agendadorTarefas.agendador.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

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
        return appointmentRepository.save(appointment);
    }
}

