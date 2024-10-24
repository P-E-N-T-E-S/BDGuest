create database if not exists GUEST;

use GUEST;

CREATE TABLE if not exists Pessoa (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(50),
    rua VARCHAR(50),
    bairro VARCHAR(50),
    estado varchar(50),
    cidade varchar(50),
    cep INTEGER,
    email VARCHAR(50),
    data_nascimento DATE,
    telefone VARCHAR(20),
    telefone_2 VARCHAR(20)
);

CREATE TABLE if not exists Funcionario (
    cpf VARCHAR(11) PRIMARY KEY,
    data_contratacao DATE,
    salario DOUBLE,
    horario_entrada TIME,
    horario_saida TIME
);

CREATE TABLE if not exists Garcom (
    cpf VARCHAR(11) PRIMARY KEY,
    cpf_gerente VARCHAR(11)
);

CREATE TABLE if not exists Estoquista (
    cpf VARCHAR(11) PRIMARY KEY,
    cpf_gerente VARCHAR(11),
    estoque INTEGER
);

CREATE TABLE if not exists Mesa (
    numero_id SMALLINT PRIMARY KEY,
    quantidade_cadeiras SMALLINT
);

CREATE TABLE if not exists Menu (
    Id INTEGER PRIMARY KEY,
    nome VARCHAR(50),
    imagem_link VARCHAR(50),
    descricao VARCHAR(100),
    preco FLOAT
);

CREATE TABLE if not exists Comanda (
    numero_id INTEGER PRIMARY KEY,
    data DATE,
    horario TIME,
    quantidade SMALLINT,
    cpf_pessoa VARCHAR(11),
    cpf_garcom VARCHAR(11),
    fk_Menu_numero INTEGER
);

CREATE TABLE if not exists Produto (
    nome VARCHAR(50) PRIMARY KEY,
    validade DATE,
    quantidade INTEGER,
    distribuidora VARCHAR(50)
);

CREATE TABLE if not exists Estoque (
    id INTEGER PRIMARY KEY,
    rua VARCHAR(50),
    numero INTEGER,
    bairro VARCHAR(50),
    estado VARCHAR(50),
    cidade VARCHAR(50),
    cep VARCHAR(50),
    refrigerado BOOLEAN
);

CREATE TABLE if not exists Usa (
    produto VARCHAR(50),
    prato_menu INTEGER,
    PRIMARY KEY (produto, prato_menu)
);

CREATE TABLE if not exists Contem (
    produto VARCHAR(50),
    estoque INTEGER,
    PRIMARY KEY (produto, estoque)
);

CREATE TABLE if not exists Cliente (
    cpf VARCHAR(11) PRIMARY KEY,
    fidelidade INTEGER,
    metodo_pagamento_1 VARCHAR(100),
    metodo_pagamento_2 VARCHAR(100)
);

CREATE TABLE if not exists Reserva (
    data DATE,
    horario_entrada DATETIME,
    quantidade_pessoas INTEGER,
    cpf_cliente VARCHAR(11),
    numero_mesa SMALLINT,
    PRIMARY KEY (cpf_cliente, data)
);

CREATE TABLE IF NOT EXISTS Atende(
    cpf_garcom VARCHAR(50),
    id_mesa SMALLINT,
    PRIMARY KEY (cpf_garcom, id_mesa)
);

ALTER TABLE Funcionario ADD CONSTRAINT FK_Funcionario_2
    FOREIGN KEY (cpf)
    REFERENCES Pessoa (cpf)
    ON DELETE CASCADE;

ALTER TABLE Garcom ADD CONSTRAINT FK_Garcom_2
    FOREIGN KEY (cpf)
    REFERENCES Funcionario (cpf)
    ON DELETE CASCADE;

ALTER TABLE Garcom ADD CONSTRAINT FK_Garcom_3
    FOREIGN KEY (cpf_gerente)
    REFERENCES Garcom (cpf)
    ON DELETE RESTRICT;

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

ALTER TABLE Comanda ADD CONSTRAINT FK_Comandas_1
    FOREIGN KEY (cpf_garcom)
    REFERENCES Garcom (cpf);

ALTER TABLE Comanda ADD CONSTRAINT FK_Comanda_3
    FOREIGN KEY (cpf_pessoa)
    REFERENCES Cliente (cpf);

ALTER TABLE Comanda ADD CONSTRAINT FK_Comanda_4
    FOREIGN KEY (fk_Menu_numero)
    REFERENCES Menu (id);

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_1
    FOREIGN KEY (produto)
    REFERENCES Produto (nome)
    ON DELETE RESTRICT;

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_2
    FOREIGN KEY (prato_menu)
    REFERENCES Menu (id)
    ON DELETE RESTRICT;

ALTER TABLE Contem ADD CONSTRAINT FK_Contem_1
    FOREIGN KEY (produto)
    REFERENCES Produto (nome)
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
    FOREIGN KEY (cpf_garcom)
    REFERENCES Garcom (cpf)
    ON DELETE RESTRICT;

ALTER TABLE Atende ADD CONSTRAINT FK_Atende_2
    FOREIGN KEY (id_mesa)
    REFERENCES Mesa (numero_id)
    ON DELETE RESTRICT;