-- Создание таблицы account
CREATE TABLE account (
                         id UUID NOT NULL CONSTRAINT account_pk PRIMARY KEY,
                         username VARCHAR(100) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         role VARCHAR(50) NOT NULL
);

-- Создание таблицы client
CREATE TABLE client (
                        id BIGSERIAL NOT NULL CONSTRAINT client_pk PRIMARY KEY,
                        account_id UUID NOT NULL
);

-- Создание таблицы specialist
CREATE TABLE specialist (
                            id BIGSERIAL NOT NULL CONSTRAINT specialist_pk PRIMARY KEY,
                            account_id UUID NOT NULL,
                            specialization VARCHAR(100) NOT NULL
);

-- Создание таблицы schedule, client_id может быть null, поскольку расписание может быть свободным
CREATE TABLE schedule (
                          id BIGSERIAL NOT NULL CONSTRAINT schedule_pk PRIMARY KEY,
                          specialist_id BIGINT NOT NULL,
                          client_id BIGINT,
                          start_time TIMESTAMP NOT NULL,
                          end_time TIMESTAMP NOT NULL
);

-- Создание таблицы consultation
CREATE TABLE consultation (
                              id BIGSERIAL NOT NULL CONSTRAINT consultation_pk PRIMARY KEY,
                              specialist_id BIGINT NOT NULL,
                              client_id BIGINT NOT NULL,
                              schedule_id BIGINT NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              reminder_sent BOOLEAN NOT NULL
);

-- Создание таблицы notification
CREATE TABLE notification (
                              id BIGSERIAL NOT NULL CONSTRAINT notification_pk PRIMARY KEY,
                              consultation_id BIGINT,
                              type VARCHAR(50) NOT NULL,
                              sent_date_time TIMESTAMP NOT NULL,
                              status VARCHAR(50) NOT NULL
);

-- Добавление внешних ключей
ALTER TABLE client ADD CONSTRAINT client_account_id_fk FOREIGN KEY (account_id) REFERENCES account (id);
ALTER TABLE specialist ADD CONSTRAINT specialist_account_id_fk FOREIGN KEY (account_id) REFERENCES account (id);
ALTER TABLE schedule ADD CONSTRAINT schedule_specialist_id_fk FOREIGN KEY (specialist_id) REFERENCES specialist (id);
ALTER TABLE schedule ADD CONSTRAINT schedule_client_id_fk FOREIGN KEY (client_id) REFERENCES client (id);
ALTER TABLE consultation ADD CONSTRAINT consultation_specialist_id_fk FOREIGN KEY (specialist_id) REFERENCES specialist (id);
ALTER TABLE consultation ADD CONSTRAINT consultation_client_id_fk FOREIGN KEY (client_id) REFERENCES client (id);
ALTER TABLE consultation ADD CONSTRAINT consultation_schedule_id_fk FOREIGN KEY (schedule_id) REFERENCES schedule (id);
ALTER TABLE notification ADD CONSTRAINT notification_consultation_id_fk FOREIGN KEY (consultation_id) REFERENCES consultation (id);

-- Добавление ограничения UNIQUE для consultation
ALTER TABLE consultation ADD CONSTRAINT unique_consultation UNIQUE (specialist_id, client_id, schedule_id);