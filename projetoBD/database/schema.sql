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
    telefone VARCHAR(20),
    telefone_2 VARCHAR(20)
);

CREATE TABLE Funcionario (
    cpf varchar(11) PRIMARY KEY,
    data_contratacao DATE,
    salario FLOAT,
    horario_entrada TIME,
    horario_saida TIME,
    CONSTRAINT fk_Pessoa_Funcionario FOREIGN KEY (cpf) REFERENCES Pessoa(cpf)
);

CREATE TABLE Cliente (
    cpf varchar(11) PRIMARY KEY,
    fidelidade INTEGER,
    metodo_pagamento1 VARCHAR(100),
    metodo_pagamento2 VARCHAR(100),
    CONSTRAINT fk_Pessoa_Cliente FOREIGN KEY (cpf) REFERENCES Pessoa(cpf)
);

CREATE TABLE Garcom (
    cpf varchar(11) PRIMARY KEY,
    cpf_gerente varchar(11),
    CONSTRAINT fk_Funcionario_Garcom FOREIGN KEY (cpf) REFERENCES Funcionario(cpf),
    CONSTRAINT fk_Gerente_Garcom FOREIGN KEY (cpf_gerente) REFERENCES Garcom(cpf)
);

CREATE TABLE Estoquista (
    cpf varchar(11) PRIMARY KEY,
    cpf_gerente varchar(11),
    estoque INTEGER,
    CONSTRAINT fk_Funcionario_Estoquista FOREIGN KEY (cpf) REFERENCES Funcionario(cpf),
    CONSTRAINT fk_Gerente_Estoquista FOREIGN KEY (cpf_gerente) REFERENCES Estoquista(cpf)
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
    mesa SMALLINT,
    CONSTRAINT fk_Cliente_Comanda FOREIGN KEY (cpf_pessoa) REFERENCES Cliente(cpf),
    CONSTRAINT fk_Mesa_Comanda FOREIGN KEY (mesa) REFERENCES Mesa(numero_id)
);

CREATE TABLE Produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
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
    estado VARCHAR(50),
    numero INTEGER
);

CREATE TABLE Usa (
    produto int,
    prato_menu INTEGER,
    quantidade INTEGER,
    PRIMARY KEY (produto, prato_menu),
    CONSTRAINT fk_Menu_Usa FOREIGN KEY (prato_menu) REFERENCES Menu(numero),
    CONSTRAINT fk_Produto_Usa FOREIGN KEY (produto) REFERENCES Produto(id)
);

CREATE TABLE Contem (
    produto int,
    estoque INTEGER,
    PRIMARY KEY (produto, estoque),
    CONSTRAINT fk_Produto_Contem FOREIGN KEY (produto) REFERENCES Produto(id),
    CONSTRAINT fk_Estoque_Contem FOREIGN KEY (estoque) REFERENCES Estoque(id)
);

CREATE TABLE Reserva (
    data DATE,
    horario_entrada TIME,
    quantidade_pessoas INTEGER,
    cpf_cliente VARCHAR(11),
    numero_mesa SMALLINT,
    PRIMARY KEY (cpf_cliente, data),
    CONSTRAINT fk_Cliente_Reserva FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf),
    CONSTRAINT fk_Mesa_Reserva FOREIGN KEY (numero_mesa) REFERENCES Mesa(numero_id)
);

CREATE TABLE Atende (
    fk_Garcom_cpf VARCHAR(11),
    fk_Mesas_numero_id SMALLINT,
    CONSTRAINT fk_Garcom_Atende FOREIGN KEY (fk_Garcom_cpf) REFERENCES Garcom(cpf),
    CONSTRAINT fk_Mesa_Atende FOREIGN KEY (fk_Mesas_numero_id) REFERENCES Mesa(numero_id)
);

CREATE TABLE Pedido (
    id_pedido INTEGER PRIMARY KEY,
    id_comanda INTEGER,
    id_menu INTEGER,
    horario DATETIME,
    quantidade INTEGER,
    CONSTRAINT fk_Comanda_Pedido FOREIGN KEY (id_comanda) REFERENCES Comanda(numero_id),
    CONSTRAINT fk_Menu_Pedido FOREIGN KEY (id_menu) REFERENCES Menu(numero)
);

CREATE TABLE Pedidos_log(
    id INT PRIMARY KEY,
    horario_pedido DATETIME,
    id_prato INTEGER,
    quantidade INT,
    nome_prato VARCHAR(50),
    cliente_bairro VARCHAR(50),
    cliente_idade INT,
    CONSTRAINT fk_Menu_PedidosLogs FOREIGN KEY (id_prato) REFERENCES Menu(numero)
);

-- Triggers

DELIMITER //
CREATE TRIGGER horario_pedido BEFORE INSERT ON Pedido
    FOR EACH ROW
    BEGIN
        SET NEW.horario = NOW();
end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER horario_comanda BEFORE INSERT ON Comanda
    FOR EACH ROW
    BEGIN
        SET NEW.acesso = NOW();
    end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER log_pedidos AFTER DELETE ON Pedido
    FOR EACH ROW
    BEGIN
        DECLARE nome_prato VARCHAR(50);
        DECLARE bairro varchar(50);
        DECLARE idade INT;

        SELECT M.nome, PE.bairro, TIMESTAMPDIFF(YEAR, PE.data_nascimento, CURDATE())
        INTO nome_prato, bairro, idade
        FROM Pedido P JOIN Comanda C on P.id_comanda = C.numero_id
        JOIN Menu M ON P.id_menu = M.numero
        JOIN Pessoa PE on C.cpf_pessoa = PE.cpf;

        INSERT INTO Pedidos_log(id, horario_pedido, id_prato, quantidade, nome_prato, cliente_bairro, cliente_idade)
        VALUES (OLD.id_pedido,OLD.horario, OLD.id_menu, OLD.quantidade,nome_prato, bairro, idade);
END //

DELIMITER ;

DELIMITER //
CREATE TRIGGER retorne_ingredientes AFTER DELETE ON Pedidos_log
    FOR EACH ROW
    BEGIN
        UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade + (u.quantidade * OLD.quantidade ) WHERE prato_menu = OLD.id_prato;
    end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER reduzir_ingredientes_update AFTER UPDATE ON Pedido
    FOR EACH ROW
    BEGIN

                IF (SELECT P.quantidade
            FROM Produto P
            JOIN Usa U2 on P.id = U2.produto
            WHERE U2.prato_menu = NEW.id_menu) < (SELECT (U.quantidade * NEW.quantidade)
                                                  FROM Usa U
                                                  where U.prato_menu = NEW.id_menu)
            THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'QUANTIDADE DE INGREDIENTES INSUFIENTES';
        end if;

        UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade + (u.quantidade * OLD.quantidade ) WHERE prato_menu = OLD.id_menu;

        UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade - (u.quantidade * NEW.quantidade ) WHERE prato_menu = NEW.id_menu;
    end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER reduzir_ingredientes_insert AFTER INSERT ON Pedido
    FOR EACH ROW
    BEGIN

        IF (SELECT P.quantidade
            FROM Produto P
            JOIN Usa U2 on P.id = U2.produto
            WHERE U2.prato_menu = NEW.id_menu) < (SELECT (U.quantidade * NEW.quantidade)
                                                  FROM Usa U
                                                  where U.prato_menu = NEW.id_menu)
            THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'QUANTIDADE DE INGREDIENTES INSUFIENTES';
        end if;

        UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade - (u.quantidade * NEW.quantidade ) WHERE prato_menu = NEW.id_menu;
    end //

DELIMITER ;

INSERT INTO Comanda (numero_id, cpf_pessoa, acesso, nome_cliente, mesa) values (4, '70963500473', null, 'Evaldo', 3)