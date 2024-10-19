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

CREATE TABLE if not exists Gerente (
    cpf VARCHAR(11) PRIMARY KEY
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

CREATE TABLE if not exists Mesas (
    numero_id SMALLINT PRIMARY KEY,
    quantidade_cadeiras SMALLINT,
    cpf_garcom VARCHAR(11)
);

CREATE TABLE if not exists Menu (
    nome VARCHAR(50),
    imagem BLOB,
    descricao VARCHAR(100),
    preco FLOAT,
    numero INTEGER PRIMARY KEY
);

CREATE TABLE if not exists Comandas (
    data DATE,
    horario TIME,
    quantidade SMALLINT,
    numero_id INTEGER PRIMARY KEY,
    cpf_pessoa VARCHAR(11),
    cpf_garcom VARCHAR(11),
    fk_Menu_numero INTEGER
);

CREATE TABLE if not exists Produtos (
    nome VARCHAR(50) PRIMARY KEY,
    validade DATE,
    quantidade INTEGER,
    distribuidora VARCHAR(50)
);

CREATE TABLE if not exists Estoque (
    rua VARCHAR(50),
    refrigerado BOOLEAN,
    bairro VARCHAR(50),
    cep VARCHAR(50),
    cidade VARCHAR(50),
    id INTEGER PRIMARY KEY
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

ALTER TABLE Funcionarios ADD CONSTRAINT FK_Funcionarios_2
    FOREIGN KEY (cpf)
    REFERENCES Pessoas (cpf)
    ON DELETE CASCADE;

ALTER TABLE Gerente ADD CONSTRAINT FK_Gerente_2
    FOREIGN KEY (cpf)
    REFERENCES Funcionarios (cpf)
    ON DELETE CASCADE;

ALTER TABLE Garcom ADD CONSTRAINT FK_Garcom_2
    FOREIGN KEY (cpf)
    REFERENCES Funcionarios (cpf)
    ON DELETE CASCADE;

ALTER TABLE Garcom ADD CONSTRAINT FK_Garcom_3
    FOREIGN KEY (cpf_gerente)
    REFERENCES Gerente (cpf)
    ON DELETE RESTRICT;

ALTER TABLE Estoquista ADD CONSTRAINT FK_Estoquista_2
    FOREIGN KEY (cpf)
    REFERENCES Funcionarios (cpf)
    ON DELETE CASCADE;

ALTER TABLE Estoquista ADD CONSTRAINT FK_Estoquista_3
    FOREIGN KEY (cpf_gerente)
    REFERENCES Estoquista (cpf);

ALTER TABLE Estoquista ADD CONSTRAINT FK_Estoquista_4
    FOREIGN KEY (estoque)
    REFERENCES Estoque (id);

ALTER TABLE Mesas ADD CONSTRAINT FK_Mesas_2
    FOREIGN KEY (cpf_garcom)
    REFERENCES Garcom (cpf)
    ON DELETE RESTRICT;

ALTER TABLE Comandas ADD CONSTRAINT FK_Comandas_1
    FOREIGN KEY (cpf_garcom)
    REFERENCES Garcom (cpf);

ALTER TABLE Comandas ADD CONSTRAINT FK_Comandas_3
    FOREIGN KEY (cpf_pessoa)
    REFERENCES Cliente (cpf);

ALTER TABLE Comandas ADD CONSTRAINT FK_Comandas_4
    FOREIGN KEY (fk_Menu_numero)
    REFERENCES Menu (numero);

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_1
    FOREIGN KEY (produto)
    REFERENCES Produtos (nome)
    ON DELETE RESTRICT;

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_2
    FOREIGN KEY (prato_menu)
    REFERENCES Menu (numero)
    ON DELETE RESTRICT;

ALTER TABLE Contem ADD CONSTRAINT FK_Contem_1
    FOREIGN KEY (produto)
    REFERENCES Produtos (nome)
    ON DELETE RESTRICT;

ALTER TABLE Contem ADD CONSTRAINT FK_Contem_2
    FOREIGN KEY (estoque)
    REFERENCES Estoque (id);

ALTER TABLE Cliente ADD CONSTRAINT FK_Cliente_1
    FOREIGN KEY (cpf)
    REFERENCES Pessoas (cpf);

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_1
    FOREIGN KEY (cpf_cliente)
    REFERENCES Cliente (cpf);

ALTER TABLE Reserva ADD CONSTRAINT FK_Reserva_3
    FOREIGN KEY (numero_mesa)
    REFERENCES Mesas (numero_id);
