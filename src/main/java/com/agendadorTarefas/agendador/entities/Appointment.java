package com.agendadorTarefas.agendador.entities;

import com.agendadorTarefas.agendador.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    public Appointment() {
    }

    public Appointment(Long id, String description, Instant moment, Status status) {
        this.id = id;
        this.description = description;
        this.moment = moment;
        setStatus(status);
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
                '}';
    }
}
