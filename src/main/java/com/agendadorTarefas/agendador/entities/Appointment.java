package com.agendadorTarefas.agendador.entities;

import com.agendadorTarefas.agendador.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150,nullable = false)
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = "America/Sao_Paulo")
    private Instant moment;
    private Integer status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    public Appointment() {
    }

    public Appointment(Long id, String description, Instant moment, Status status) {
        this.id = id;
        this.description = description;
        this.moment = moment;
        setStatus(status);
    }

    public Appointment(Long id, String description, Instant moment, Integer status, Task task, Client client, Worker worker) {
        this.id = id;
        this.description = description;
        this.moment = moment;
        this.status = status;
        this.task = task;
        this.client = client;
        this.worker = worker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Status getStatus() {
        return Status.valueOf(this.status);
    }

    public void setStatus(Status status) {
        if(status != null){
            this.status = status.getCode();
        }
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", moment=" + moment +
                ", status=" + status +
                ", task=" + task +
                ", client=" + client +
                ", worker=" + worker +
                '}';
    }
}
