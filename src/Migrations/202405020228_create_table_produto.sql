CREATE TABLE produto (
    id SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    descricao TEXT,
    quantidade_estoque INT NOT NULL,
    preco NUMERIC(10,2) DEFAULT 10.00,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);