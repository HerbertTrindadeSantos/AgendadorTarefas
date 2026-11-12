package com.agendadorTarefas.agendador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(length = 50,nullable = false)
    private String surname;

    @JsonIgnore
    @OneToMany(mappedBy = "worker",fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    public Worker(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
