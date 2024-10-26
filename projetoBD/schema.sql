CREATE DATABASE IF NOT EXISTS GUEST;

USE GUEST;

CREATE TABLE Pessoa (
    cpf INTEGER PRIMARY KEY,
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
    cpf INTEGER PRIMARY KEY,
    data_contratacao DATE,
    salario FLOAT,
    horario_entrada TIME,
    horario_saida TIME
);

CREATE TABLE Garcom (
    cpf INTEGER PRIMARY KEY,
    cpf_gerente INTEGER
);

CREATE TABLE Estoquista (
    cpf INTEGER PRIMARY KEY,
    cpf_gerente INTEGER,
    estoque INTEGER
);

CREATE TABLE Mesa (
    numero_id SMALLINT PRIMARY KEY,
    quantidade_cadeiras SMALLINT,
    cpf_garcom INTEGER
);

CREATE TABLE Menu (
    nome VARCHAR(50),
    imagem BLOB,
    descricao VARCHAR(100),
    preco FLOAT,
    numero INTEGER PRIMARY KEY
);

CREATE TABLE Comanda (
    numero_id INTEGER PRIMARY KEY,
    cpf_pessoa INTEGER,
    acesso DATETIME,
    nome_cliente VARCHAR(50)
);

CREATE TABLE Produto (
    nome VARCHAR(100) PRIMARY KEY,
    validade DATE,
    quantidade INTEGER,
    distribuidora VARCHAR(50)
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
    produto VARCHAR(100),
    prato_menu INTEGER,
    PRIMARY KEY (produto, prato_menu)
);

CREATE TABLE Contem (
    produto VARCHAR(100),
    estoque INTEGER,
    PRIMARY KEY (produto, estoque)
);

CREATE TABLE Cliente (
    fidelidade INTEGER,
    metodo_pagamento1 VARCHAR(100),
    metodo_pagamento2 VARCHAR(100),
    cpf INTEGER PRIMARY KEY
);

CREATE TABLE Reserva (
    data DATE,
    horario_entrada DATETIME,
    quantidade_pessoas INTEGER,
    cpf_cliente INTEGER,
    numero_mesa SMALLINT,
    PRIMARY KEY (cpf_cliente, data)
);

CREATE TABLE Atende (
    fk_Garcom_cpf INTEGER,
    fk_Mesas_numero_id SMALLINT
);

CREATE TABLE Pedido (
    fk_Comandas_numero_id INTEGER,
    fk_Menu_numero INTEGER,
    horario DATETIME,
    quantidade INTEGER,
    PRIMARY KEY (horario, fk_Comandas_numero_id, fk_Menu_numero)
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

ALTER TABLE Mesa ADD CONSTRAINT FK_Mesas_2
    FOREIGN KEY (cpf_garcom)
    REFERENCES Garcom (cpf)
    ON DELETE RESTRICT;

ALTER TABLE Comanda ADD CONSTRAINT FK_Comandas_2
    FOREIGN KEY (cpf_pessoa)
    REFERENCES Cliente (cpf);

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_1
    FOREIGN KEY (produto)
    REFERENCES Produto (nome)
    ON DELETE RESTRICT;

ALTER TABLE Usa ADD CONSTRAINT FK_Usa_3
    FOREIGN KEY (prato_menu)
    REFERENCES Menu (numero);

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
    FOREIGN KEY (fk_Garcom_cpf)
    REFERENCES Garcom (cpf);

ALTER TABLE Atende ADD CONSTRAINT FK_Atende_2
    FOREIGN KEY (fk_Mesas_numero_id)
    REFERENCES Mesa (numero_id);

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_1
    FOREIGN KEY (fk_Comandas_numero_id)
    REFERENCES Comanda (numero_id);

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_2
    FOREIGN KEY (fk_Menu_numero)
    REFERENCES Menu (numero);