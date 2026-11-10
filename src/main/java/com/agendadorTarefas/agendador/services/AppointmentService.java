package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.exception.NotFoundAppointmentException;
import com.agendadorTarefas.agendador.repositories.AppointmentRepository;
import com.agendadorTarefas.agendador.services.validation.AppointmentValidator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentValidator appointmentValidator;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentValidator  appointmentValidator ) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentValidator = appointmentValidator ;
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundAppointmentException("Not Found Appointment id: " + id));
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Transactional
    public Appointment create(Appointment appointment) {
        appointmentValidator.validate(appointment);
        return appointmentRepository.save(appointment);
    }
}
