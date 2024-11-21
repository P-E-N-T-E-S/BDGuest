USE GUEST;

-- Povoando a tabela Pessoa
INSERT INTO Pessoa (cpf, nome, rua, bairro, estado, cidade, cep, email, data_nascimento, telefone, telefone_2) VALUES
('12345678909', 'João Silva', 'Rua A', 'Centro', 'SP', 'São Paulo', 12345678, 'joao@gmail.com', '1990-05-20', '(11) 99999-1111', '(11) 98888-1111'),
('98765432100', 'Maria Oliveira', 'Rua B', 'Jardim', 'RJ', 'Rio de Janeiro', 23456789, 'maria@gmail.com', '1985-09-15', '(21) 97777-2222', NULL),
('19283746522', 'Carlos Souza', 'Rua C', 'Vila Nova', 'MG', 'Belo Horizonte', 34567890, 'carlos@gmail.com', '1995-03-10', '(31) 96666-3333', '(31) 95555-3333'),
('45678912300', 'Ana Pereira', 'Rua E', 'Centro', 'SP', 'São Paulo', 56789012, 'ana.pereira@gmail.com', '1993-07-25', '(11) 91111-4444', NULL),
('78912345600', 'Rafael Santos', 'Rua F', 'Industrial', 'PR', 'Curitiba', 67890123, 'rafael.santos@gmail.com', '1998-11-05', '(41) 92222-5555', '(41) 93333-5555');


-- Povoando a tabela Funcionario
INSERT INTO Funcionario (cpf, data_contratacao, salario, horario_entrada, horario_saida) VALUES
('12345678909', '2022-01-10', 3000.00, '08:00:00', '16:00:00'),
('98765432100', '2021-06-15', 3500.00, '09:00:00', '17:00:00'),
('45678912300', '2023-02-01', 3200.00, '10:00:00', '18:00:00'),
('78912345600', '2023-03-15', 2800.00, '08:00:00', '16:00:00');



-- Povoando a tabela Cliente
INSERT INTO Cliente (cpf, fidelidade, metodo_pagamento1, metodo_pagamento2) VALUES
('19283746522', 5, 'Cartão de Crédito', 'Pix');

-- Povoando a tabela Garcom
INSERT INTO Garcom (cpf, cpf_gerente) VALUES
('12345678909', NULL),
('45678912300', '12345678909');

INSERT INTO Estoque (id, rua, refrigerado, bairro, cep, cidade, estado, numero) VALUES
(1, 'Rua D', TRUE, 'Centro', '12345678', 'São Paulo', 'SP', 50),
(2, 'Rua E', TRUE, 'Centro', '87654321', 'São Paulo', 'SP', 100);

-- Povoando a tabela Estoquista
INSERT INTO Estoquista (cpf, cpf_gerente, estoque) VALUES
('98765432100', NULL, 1),
('78912345600', '98765432100', 2);

-- Povoando a tabela Mesa
INSERT INTO Mesa (numero_id, quantidade_cadeiras) VALUES
(1, 4),
(2, 6),
(3, 2);

-- Povoando a tabela Menu
INSERT INTO Menu (nome, imagem, descricao, preco, numero) VALUES
('Pizza Margherita', 'pizza.jpg', 'Pizza clássica com molho de tomate e manjericão.', 35.50, 1),
('Lasanha à Bolonhesa', 'lasanha.jpg', 'Lasanha recheada com molho à bolonhesa e queijo.', 45.00, 2);

-- Povoando a tabela Produto
INSERT INTO Produto (nome, validade, quantidade, distribuidora, medida) VALUES
('Queijo Mussarela', '2024-01-10', 20, 'Distribuidora A', 'kg'),
('Molho de Tomate', '2023-12-15', 30, 'Distribuidora B', 'litros');

-- Povoando a tabela Usa
INSERT INTO Usa (produto, prato_menu, quantidade) VALUES
(1, 1, 2),
(2, 1, 1);

-- Povoando a tabela Contem
INSERT INTO Contem (produto, estoque) VALUES
(1, 1),
(2, 1);

-- Povoando a tabela Comanda
INSERT INTO Comanda (cpf_pessoa, acesso, nome_cliente, mesa, cpf_garcom, chamando_garcom) VALUES
('19283746522', NOW(), 'Carlos Souza', 1, '12345678909', FALSE);

-- Povoando a tabela Pedido
INSERT INTO Pedido (id_pedido, id_comanda, id_menu, horario, quantidade, status) VALUES
(1, 1, 1, NOW(), 2, 'FAZENDO');

-- Povoando a tabela Atende
INSERT INTO Atende (fk_Garcom_cpf, fk_Mesas_numero_id) VALUES
('12345678909', 1),
('12345678909', 2);

-- Povoando a tabela Pedido
INSERT INTO Pedido (id_pedido, id_comanda, id_menu, horario, quantidade, status) VALUES
(2, 1, 2, NOW(), 1, 'FAZENDO'),
(3, 1, 1, NOW(), 3, 'FAZENDO');

-- Povoando a tabela Reserva
INSERT INTO Reserva (data, horario_entrada, quantidade_pessoas, cpf_cliente, numero_mesa) VALUES
('2024-12-25', '20:00:00', 4, '19283746522', 2),
('2024-12-31', '21:00:00', 2, '19283746522', 3);
