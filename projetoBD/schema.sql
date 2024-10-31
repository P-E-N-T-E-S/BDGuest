CREATE DATABASE IF NOT EXISTS GUEST;

USE GUEST;

CREATE TABLE Pessoa (
    cpf varchar(11) PRIMARY KEY,
    nome VARCHAR(50),
    rua VARCHAR(50),
    bairro VARCHAR(50),
    estado VARCHAR(50),
    cidade VARCHAR(50),
    cep INTEGER,
    email VARCHAR(50),
    data_nascimento DATE,
    telefone INTEGER,
    telefone_2 INTEGER
);

CREATE TABLE Funcionario (
    cpf varchar(11) PRIMARY KEY,
    data_contratacao DATE,
    salario FLOAT,
    horario_entrada TIME,
    horario_saida TIME
);

CREATE TABLE Garcom (
    cpf varchar(11) PRIMARY KEY,
    cpf_gerente varchar(11)
);

CREATE TABLE Estoquista (
    cpf varchar(11) PRIMARY KEY,
    cpf_gerente varchar(11),
    estoque INTEGER
);

CREATE TABLE Mesa (
    numero_id SMALLINT PRIMARY KEY,
    quantidade_cadeiras SMALLINT
);

CREATE TABLE Menu (
    nome VARCHAR(50),
    imagem VARCHAR(50),
    descricao VARCHAR(100),
    preco FLOAT,
    numero INTEGER PRIMARY KEY
);

CREATE TABLE Comanda (
    numero_id INTEGER PRIMARY KEY,
    cpf_pessoa varchar(11),
    acesso DATETIME,
    nome_cliente VARCHAR(50),
    mesa SMALLINT
);

CREATE TABLE Produto (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    validade DATE,
    quantidade INTEGER,
    distribuidora VARCHAR(50),
    medida VARCHAR(10)
);

CREATE TABLE Estoque (
    id INTEGER PRIMARY KEY,
    rua VARCHAR(50),
    refrigerado BOOLEAN,
    bairro VARCHAR(50),
    cep VARCHAR(50),
    cidade VARCHAR(50),
    numero INTEGER
);

CREATE TABLE Usa (
    produto int,
    prato_menu INTEGER,
    quantidade INTEGER,
    PRIMARY KEY (produto, prato_menu)
);

CREATE TABLE Contem (
    produto int,
    estoque INTEGER,
    PRIMARY KEY (produto, estoque)
);

CREATE TABLE Cliente (
    cpf varchar(11) PRIMARY KEY,
    fidelidade INTEGER,
    metodo_pagamento1 VARCHAR(100),
    metodo_pagamento2 VARCHAR(100)
);

CREATE TABLE Reserva (
    data DATE,
    horario_entrada TIMESTAMP,
    quantidade_pessoas INTEGER,
    cpf_cliente VARCHAR(11),
    numero_mesa SMALLINT,
    PRIMARY KEY (cpf_cliente, data)
);

CREATE TABLE Atende (
    fk_Garcom_cpf VARCHAR(11),
    fk_Mesas_numero_id SMALLINT
);

CREATE TABLE Pedido (
    id_pedido INTEGER PRIMARY KEY,
    id_comanda INTEGER,
    id_menu INTEGER,
    horario DATETIME,
    quantidade INTEGER,

);

ALTER TABLE Funcionario ADD CONSTRAINT FK_Funcionarios_2
    FOREIGN KEY (cpf)
    REFERENCES Pessoa (cpf)
    ON DELETE CASCADE;

ALTER TABLE Garcom ADD CONSTRAINT FK_Garcom_2
    FOREIGN KEY (cpf)
    REFERENCES Funcionario (cpf)
    ON DELETE CASCADE;

ALTER TABLE Garcom ADD CONSTRAINT FK_Garcom_3
    FOREIGN KEY (cpf_gerente)
    REFERENCES Garcom (cpf);

ALTER TABLE Estoquista ADD CONSTRAINT FK_Estoquista_2
    FOREIGN KEY (cpf)
    REFERENCES Funcionario (cpf)
    ON DELETE CASCADE;

ALTER TABLE Estoquista ADD CONSTRAINT FK_Estoquista_3
    FOREIGN KEY (cpf_gerente)
    REFERENCES Estoquista (cpf);

ALTER TABLE Estoquista ADD CONSTRAINT FK_Estoquista_4
    FOREIGN KEY (estoque)
    REFERENCES Estoque (id);

ALTER TABLE Comanda ADD CONSTRAINT FK_Comandas_2
    FOREIGN KEY (cpf_pessoa)
    REFERENCES Cliente (cpf);

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_1
    FOREIGN KEY (produto)
    REFERENCES Produto (id)
    ON DELETE RESTRICT;

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_3
    FOREIGN KEY (prato_menu)
    REFERENCES Menu (numero);

ALTER TABLE Contem ADD CONSTRAINT FK_Contem_1
    FOREIGN KEY (produto)
    REFERENCES Produto (id)
    ON DELETE RESTRICT;

ALTER TABLE Contem ADD CONSTRAINT FK_Contem_2
    FOREIGN KEY (estoque)
    REFERENCES Estoque (id);

ALTER TABLE Cliente ADD CONSTRAINT FK_Cliente_1
    FOREIGN KEY (cpf)
    REFERENCES Pessoa (cpf);

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_1
    FOREIGN KEY (cpf_cliente)
    REFERENCES Cliente (cpf);

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_3
    FOREIGN KEY (numero_mesa)
    REFERENCES Mesa (numero_id);

ALTER TABLE Atende ADD CONSTRAINT FK_Atende_1
    FOREIGN KEY (fk_Garcom_cpf)
    REFERENCES Garcom (cpf);

ALTER TABLE Atende ADD CONSTRAINT FK_Atende_2
    FOREIGN KEY (fk_Mesas_numero_id)
    REFERENCES Mesa (numero_id);

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_1
    FOREIGN KEY (id_comanda)
    REFERENCES Comanda (numero_id);

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_2
    FOREIGN KEY (id_menu)
    REFERENCES Menu (numero);

ALTER TABLE Comanda ADD CONSTRAINT FK_mesa_1
    FOREIGN KEY (mesa)
    REFERENCES Mesa (numero_id);

SELECT * FROM Mesa M JOIN Comanda C on C.mesa = M.numero_id