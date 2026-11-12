package com.agendadorTarefas.agendador.controllers;

import com.agendadorTarefas.agendador.dtos.AppointmentRequest;
import com.agendadorTarefas.agendador.dtos.AppointmentResponse;
import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> findById(@PathVariable Long id){

        AppointmentResponse response = appointmentService.findById(id);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    public ResponseEntity<List<AppointmentResponse>> findAll(){
        List<AppointmentResponse> appointments = appointmentService.findAll();
        return ResponseEntity.ok().body(appointments);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> insert(@Valid @RequestBody AppointmentRequest request){

        AppointmentResponse response = appointmentService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
