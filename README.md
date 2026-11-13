# Agendador de Tarefas e Agendamentos (Agendador API)

## 📌 Descrição do Problema

O sistema **Agendador API** foi desenvolvido para solucionar a gestão e o controle de agendamentos de serviços técnicos (como manutenções e instalações de ar-condicionado). A aplicação automatiza o vínculo entre **clientes** solicitantes, **trabalhadores** (técnicos) responsáveis e **tarefas** disponíveis, evitando conflitos de horários através de restrições de unicidade e regras de negócio robustas.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21** e **Spring Boot 3.3.5** (Spring Data JPA / Hibernate)
* **PostgreSQL** (banco de dados relacional)
* **Flyway** (migrações e versionamento do banco)
* **Docker e Docker Compose** (containerização e orquestração do ambiente)
* **JUnit 5 e MockMvc** (testes automatizados de integração/controllers)
* **Springdoc OpenAPI (Swagger)** (documentação interativa da API)
* **Render** (deploy em nuvem)

---

## ☁️ Deploy

A API está publicada no Render:

* **URL base:** https://agendadortarefas.onrender.com
* **Swagger UI:** https://agendadortarefas.onrender.com/swagger-ui/index.html

> No plano gratuito do Render, a aplicação "dorme" após alguns minutos sem uso. A primeira requisição pode levar cerca de 50 segundos para responder.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* Docker e Docker Compose instalados (recomendado)
* Java 21 e Maven instalados (apenas se for rodar sem Docker)

### Opção 1: Docker Compose (recomendado)

Na raiz do projeto (onde está o `docker-compose.yml`):

```bash
docker compose up --build -d
```

A aplicação sobe junto com o PostgreSQL e fica acessível em `http://localhost:8080`.

Para parar o ambiente:

```bash
docker compose down
```

### Opção 2: Maven (local)

Tenha um PostgreSQL rodando e configure as credenciais no `application.yml` ou por variáveis de ambiente. <!-- CONFIRMAR nomes das variáveis -->

```bash
mvn spring-boot:run
```

---

## 🧪 Como Executar os Testes

A suíte cobre cadastro válido, cadastro inválido e consultas:

```bash
mvn test
```

---

## 🌐 Endpoints Disponíveis

Documentação interativa (Swagger UI):

* Local: http://localhost:8080/swagger-ui/index.html
* Produção: https://agendadortarefas.onrender.com/swagger-ui/index.html

| Recurso       | Método | Rota                | Descrição                          |
|---------------|--------|---------------------|------------------------------------|
| Clientes      | GET    | `/client`           | Lista todos os clientes            |
|               | GET    | `/client/{id}`      | Busca cliente por ID               |
|               | POST   | `/client`           | Cadastra um novo cliente           |
| Trabalhadores | GET    | `/worker`           | Lista todos os trabalhadores       |
|               | GET    | `/worker/{id}`      | Busca trabalhador por ID           |
|               | POST   | `/worker`           | Cadastra um novo trabalhador       |
| Tarefas       | GET    | `/task`             | Lista todas as tarefas disponíveis |
|               | GET    | `/task/{id}`        | Busca tarefa por ID                |
|               | POST   | `/task`             | Cadastra uma nova tarefa           |
| Agendamentos  | GET    | `/appointment`      | Lista todos os agendamentos        |
|               | GET    | `/appointment/{id}` | Busca agendamento por ID           |
|               | POST   | `/appointment`      | Cria um novo agendamento           |

---

## 📥 Exemplos de Requisições

### 1. Cadastrar um cliente (`POST /client`)

```bash
curl -X POST http://localhost:8080/client \
  -H "Content-Type: application/json" \
  -d '{"name": "Tiago", "surname": "Henrique"}'
```

### 2. Cadastrar um agendamento (`POST /appointment`)

**Request body:**

```json
{
    "description": "Limpeza simples de Ar condicionado",
    "moment": "15:30 15/11/2026",
    "status": "PENDING",
    "taskId": 1,
    "clientId": 1,
    "workerId": 1
}
```

**Resposta esperada (201 Created):**

```json
{
    "id": 1,
    "description": "Limpeza simples de Ar condicionado",
    "moment": "15:30 15/11/2026",
    "status": "PENDING",
    "task": { "id": 1, "description": "Limpeza simples de Ar condicionado" },
    "client": { "id": 1, "name": "Tiago", "surname": "Henrique" },
    "worker": { "id": 1, "name": "Joao", "surname": "Carlos" }
}
```

### 3. Agendamento com conflito de horário

Enviar novamente o mesmo `workerId` e `moment` retorna erro, pois o horário já está ocupado. <!-- CONFIRMAR status (ex.: 409 Conflict) -->

### 4. Buscar agendamento por ID (`GET /appointment/1`)

```bash
curl http://localhost:8080/appointment/1
```

### 5. Listar agendamentos em produção

```bash
curl https://agendadortarefas.onrender.com/appointment
```

---

## 👥 Integrantes da Equipe

* Herbert
* Raul
* Leydson Renan
* Pedro Joao
* Lucas Gabriel
