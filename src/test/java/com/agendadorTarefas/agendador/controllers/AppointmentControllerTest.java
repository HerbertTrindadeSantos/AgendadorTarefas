package com.agendadorTarefas.agendador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Teste 1 - Cadastro válido: Deve criar um agendamento com sucesso")
    void shouldCreateAppointmentSuccessfully() throws Exception {
        String appointmentJson = """
            {
                "description": "Consulta de rotina",
                "moment": "15:30 19/11/2026",
                "status": "PENDING",
                "taskId": 1,
                "clientId": 1,
                "workerId": 1
            }
            """;

        mockMvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").value("Consulta de rotina"));
    }

    @Test
    @DisplayName("Teste 2 - Cadastro inválido: Deve retornar erro ao violar regras de negócio")
    void shouldFailWhenAppointmentIsInvalid() throws Exception {
        String invalidAppointmentJson = """
            {
                "description": "",
                "moment": null,
                "status": "PENDING",
                "taskId": null,
                "clientId": null,
                "workerId": null
            }
            """;

        mockMvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidAppointmentJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Teste 3 - Consulta: Deve retornar a lista de agendamentos cadastrados")
    void shouldListAppointmentsSuccessfully() throws Exception {
        mockMvc.perform(get("/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}