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
    numero_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    cpf_pessoa varchar(11),
    acesso DATETIME,
    nome_cliente VARCHAR(50),
    mesa SMALLINT,
    cpf_garcom VARCHAR(11),
    chamando_garcom BOOLEAN,
    CONSTRAINT fk_Cliente_Comanda FOREIGN KEY (cpf_pessoa) REFERENCES Cliente(cpf),
    CONSTRAINT fk_Mesa_Comanda FOREIGN KEY (mesa) REFERENCES Mesa(numero_id),
    CONSTRAINT fk_Garcom_Comanda FOREIGN KEY (cpf_garcom) REFERENCES Garcom(cpf)
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
    status VARCHAR(10),
    CONSTRAINT fk_Comanda_Pedido FOREIGN KEY (id_comanda) REFERENCES Comanda(numero_id),
    CONSTRAINT fk_Menu_Pedido FOREIGN KEY (id_menu) REFERENCES Menu(numero),
    CONSTRAINT check_status CHECK ( status IN ('PRONTO', 'FAZENDO', 'ENTREGUE') )
);

CREATE TABLE Pedidos_log( -- TODO: Colocar o cpf do garcom aq
    id INT PRIMARY KEY,
    horario_pedido DATETIME,
    id_prato INTEGER,
    quantidade INT,
    nome_prato VARCHAR(50),
    cliente_bairro VARCHAR(50),
    cliente_idade INT,
    cpf_garcom VARCHAR(11),
    CONSTRAINT fk_Menu_PedidosLogs FOREIGN KEY (id_prato) REFERENCES Menu(numero),
    CONSTRAINT fk_Garcom_PedidosLogs FOREIGN KEY (cpf_garcom) REFERENCES Garcom(cpf)
);

-- Triggers

DELIMITER //
CREATE TRIGGER horario_pedido BEFORE INSERT ON Pedido
    FOR EACH ROW
    BEGIN
        SET NEW.horario = NOW();
        SET NEW.status = 'FAZENDO';
end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER inicializar_comanda BEFORE INSERT ON Comanda
    FOR EACH ROW
    BEGIN
        SET NEW.acesso = NOW();
        SET NEW.chamando_garcom = FALSE;
    end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER log_pedidos AFTER DELETE ON Pedido
    FOR EACH ROW
    BEGIN
        DECLARE nome_prato VARCHAR(50);
        DECLARE bairro varchar(50);
        DECLARE idade INT;
        DECLARE garcom_cpf VARCHAR(11);

        SELECT M.nome, PE.bairro, TIMESTAMPDIFF(YEAR, PE.data_nascimento, CURDATE()), cpf_garcom
        INTO nome_prato, bairro, idade, garcom_cpf
        FROM Pedido P JOIN Comanda C on P.id_comanda = C.numero_id
        JOIN Menu M ON P.id_menu = M.numero
        JOIN Pessoa PE on C.cpf_pessoa = PE.cpf
        JOIN Garcom G on G.cpf = C.cpf_garcom;

        INSERT INTO Pedidos_log(id, horario_pedido, id_prato, quantidade, nome_prato, cliente_bairro, cliente_idade, cpf_garcom)
        VALUES (OLD.id_pedido,OLD.horario, OLD.id_menu, OLD.quantidade,nome_prato, bairro, idade , garcom_cpf);
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

        DECLARE produto VARCHAR(30);

        SELECT P2.nome
        INTO produto
        FROM Produto P2
        JOIN Usa U2 ON P2.id = U2.produto
        JOIN Menu M on U2.prato_menu = M.numero
        WHERE P2.quantidade < (U2.quantidade * NEW.quantidade)
        LIMIT 1;

        IF produto IS NOT NULL
            THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'QUANTIDADE DE INGREDIENTES INSUFIENTES';
        end if;

        UPDATE Produto p JOIN Usa u ON p.id = u.produto SET p.quantidade = p.quantidade - (u.quantidade * NEW.quantidade ) WHERE prato_menu = NEW.id_menu;
    end //

DELIMITER ;

DELIMITER //
CREATE TRIGGER inicializar_cliente_fidelidade BEFORE INSERT ON Cliente
    FOR EACH ROW
    BEGIN
        SET NEW.fidelidade = 0;
    end //

DELIMITER ;

DELIMITER //
CREATE FUNCTION garcom_atendente (id_mesa INT)
    RETURNS VARCHAR(11)
    READS SQL DATA
    BEGIN

    DECLARE cpf_garcom VARCHAR(11);

    SELECT A.fk_Garcom_cpf
    INTO cpf_garcom
    FROM Atende A
    JOIN Garcom G on A.fk_Garcom_cpf = G.cpf
    JOIN Funcionario F on G.cpf = F.cpf
    WHERE fk_Mesas_numero_id = id_mesa
    AND CURRENT_TIME BETWEEN horario_entrada AND horario_saida
    LIMIT 1;

    RETURN cpf_garcom;

    END //
DELIMITER ;

SELECT P.nome, COUNT(*) as p_realizados
FROM Pedidos_log PL
JOIN Garcom G on PL.cpf_garcom = G.cpf
JOIN Funcionario F ON G.cpf = F.cpf
JOIN Pessoa P on F.cpf = P.cpf
GROUP BY P.nome
HAVING COUNT(*) > (SELECT AVG(PE.pedidos) FROM (SELECT COUNT(*) as pedidos FROM Pedidos_log PL GROUP BY PL.cpf_garcom) as PE)