package com.agendadorTarefas.agendador.services;

import com.agendadorTarefas.agendador.dtos.AppointmentRequest;
import com.agendadorTarefas.agendador.dtos.AppointmentResponse;
import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.entities.Client;
import com.agendadorTarefas.agendador.entities.Task;
import com.agendadorTarefas.agendador.entities.Worker;
import com.agendadorTarefas.agendador.entities.enums.Status;
import com.agendadorTarefas.agendador.exception.NotFoundAppointmentException;
import com.agendadorTarefas.agendador.exception.NotFoundClientException;
import com.agendadorTarefas.agendador.exception.NotFoundTaskException;
import com.agendadorTarefas.agendador.exception.NotFoundWorkerException;
import com.agendadorTarefas.agendador.mappers.AppointmentMapper;
import com.agendadorTarefas.agendador.repositories.AppointmentRepository;
import com.agendadorTarefas.agendador.repositories.ClientRepository;
import com.agendadorTarefas.agendador.repositories.TaskRepository;
import com.agendadorTarefas.agendador.repositories.WorkerRepository;
import com.agendadorTarefas.agendador.services.validation.AppointmentValidator;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentValidator appointmentValidator;
    private final AppointmentMapper appointmentMapper;
    private final TaskRepository taskRepository;
    private final WorkerRepository workerRepository;
    private final ClientRepository clientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentValidator appointmentValidator,
                              AppointmentMapper appointmentMapper, TaskRepository taskRepository, WorkerRepository workerRepository, ClientRepository clientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentValidator = appointmentValidator;
        this.appointmentMapper = appointmentMapper;
        this.taskRepository = taskRepository;
        this.workerRepository = workerRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public AppointmentResponse findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundAppointmentException("Not Found Appointment id: " + id));
        return appointmentMapper.toResponse(appointment);
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponse> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toResponse).toList();
    }

    @Transactional
    public AppointmentResponse create(AppointmentRequest request) {

        Task task = taskRepository.findById(request.taskId()).
                orElseThrow(() -> new NotFoundTaskException("Task not found"));

        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new NotFoundClientException("Client not found"));

        Worker worker = workerRepository.findById(request.workerId())
                .orElseThrow(() -> new NotFoundWorkerException("Client not found"));

        Appointment appointment = new Appointment(null, request.description(),request.moment(),
                Status.valueOf(request.status()),task,client,worker);

        appointmentValidator.validate(appointment);

        appointmentRepository.save(appointment);

        return appointmentMapper.toResponse(appointment) ;
    }
}
