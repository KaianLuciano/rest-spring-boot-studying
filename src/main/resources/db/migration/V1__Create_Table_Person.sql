CREATE TABLE tb_person
(
    person_id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    address    VARCHAR(255),
    gender     VARCHAR(255),
    CONSTRAINT pk_tb_person PRIMARY KEY (person_id)
);