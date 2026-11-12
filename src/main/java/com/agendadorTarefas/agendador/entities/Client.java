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
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(length = 50,nullable = false)
    private String surname;

    @Column(length = 11,nullable = false,unique = true)
    private String cellPhone;

    @Column(length = 200,nullable = false,unique = true)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    public Client(Long id, String name, String surname, String cellPhone, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cellPhone = cellPhone;
        this.address = address;
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
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
