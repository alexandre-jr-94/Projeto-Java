CREATE TABLE usuario (
    id SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    senha VARCHAR(500) NOT NULL,
    email VARCHAR(150),
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);