package com.agendadorTarefas.agendador.mappers;

import com.agendadorTarefas.agendador.dtos.*;
import com.agendadorTarefas.agendador.entities.Appointment;
import com.agendadorTarefas.agendador.entities.Client;
import com.agendadorTarefas.agendador.entities.Task;
import com.agendadorTarefas.agendador.entities.Worker;
import com.agendadorTarefas.agendador.entities.enums.Status;
import org.springframework.stereotype.Component;


@Component
public class AppointmentMapper {

    public Appointment toEntity(AppointmentRequest appointmentRequest, Task task, Client client, Worker worker) {
        Appointment appointment = new Appointment();
        appointment.setId(null);
        appointment.setDescription(appointmentRequest.description());
        appointment.setMoment(appointmentRequest.moment());
        appointment.setStatus(Status.valueOf(appointmentRequest.status()));
        appointment.setTask(task);
        appointment.setClient(client);
        appointment.setWorker(worker);

        return appointment;
    }

    public AppointmentResponse toResponse(Appointment appointment) {

        TaskSummary taskSummary = new TaskSummary(appointment.getTask().getId(), appointment.getTask().getDescription());

        ClientSummary clientSummary = new ClientSummary(appointment.getClient().getId(), appointment.getClient().getName()
                , appointment.getClient().getSurname(), appointment.getClient().getAddress(), appointment.getClient().getCellPhone());

        WorkerSummary workerSummary = new WorkerSummary(appointment.getWorker().getId()
                , appointment.getWorker().getName(), appointment.getWorker().getSurname());


        return new AppointmentResponse(
                appointment.getId(),
                appointment.getDescription(),
                appointment.getMoment(),
                appointment.getStatus().toString(),
                taskSummary,
                clientSummary,
                workerSummary
        );
    }
}
