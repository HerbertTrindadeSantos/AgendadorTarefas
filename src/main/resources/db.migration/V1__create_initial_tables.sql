CREATE TABLE tb_client
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    surname    VARCHAR[50) NOT NULL,
    cell_phone VARCHAR(11) NOT NULL UNIQUE,
    address    VARCHAR(200) NOT NULL
);

CREATE TABLE tb_task
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR(150) NOT NULL UNIQUE,
);

CREATE TABLE tb_worker
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL
);

CREATE TABLE tb_appointment
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR(150) NOT NULL,
    moment      TIMESTAMP NOT NULL,
    status      VARCHAR(30) NOT NULL,
    task_id     BIGINT REFERENCES tb_task (id),
    client_id   BIGINT REFERENCES tb_client (id),
    worker_id   BIGINT REFERENCES tb_worker (id),
    CONSTRAINT uq_appointment_worker_moment UNIQUE (worker_id,moment)

);