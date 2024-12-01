-- Создание таблицы account
CREATE TABLE account (
                         id UUID PRIMARY KEY,
                         username VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         role VARCHAR(50) NOT NULL
);

-- Создание таблицы client
CREATE TABLE client (
                        id BIGSERIAL PRIMARY KEY,
                        account_id UUID,
                        FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Создание таблицы specialist
CREATE TABLE specialist (
                            id BIGSERIAL PRIMARY KEY,
                            account_id UUID,
                            specialization VARCHAR(255),
                            FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Создание таблицы schedule
CREATE TABLE schedule (
                          id BIGSERIAL PRIMARY KEY,
                          specialist_id BIGINT,
                          client_id BIGINT,
                          date DATE,
                          start_time TIME,
                          end_time TIME,
                          FOREIGN KEY (specialist_id) REFERENCES specialist(id),
                          FOREIGN KEY (client_id) REFERENCES client(id)
);

-- Создание таблицы consultation
CREATE TABLE consultation (
                              id BIGSERIAL PRIMARY KEY,
                              specialist_id BIGINT,
                              client_id BIGINT,
                              schedule_id BIGINT,
                              status VARCHAR(50),
                              reminder_sent BOOLEAN,
                              FOREIGN KEY (specialist_id) REFERENCES specialist(id),
                              FOREIGN KEY (client_id) REFERENCES client(id),
                              FOREIGN KEY (schedule_id) REFERENCES schedule(id)
);

-- Создание таблицы notification
CREATE TABLE notification (
                              id BIGSERIAL PRIMARY KEY,
                              consultation_id BIGINT,
                              type VARCHAR(50),
                              sent_date_time TIMESTAMP,
                              status VARCHAR(50),
                              FOREIGN KEY (consultation_id) REFERENCES consultation(id)
);