package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(Long id){

        Appointment appointment = appointmentService.findById(id);

        return ResponseEntity.ok().body(appointment);
    }

    @GetMapping()
    public ResponseEntity<List<Appointment>> findAll(){
        List<Appointment> appointments = appointmentService.findAll();
        return ResponseEntity.ok().body(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointment> insert(Appointment appointment){

        Appointment newAppointment = appointmentService.create(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newAppointment.getId()).toUri();

        return ResponseEntity.created(uri).body(newAppointment);
    }
}
