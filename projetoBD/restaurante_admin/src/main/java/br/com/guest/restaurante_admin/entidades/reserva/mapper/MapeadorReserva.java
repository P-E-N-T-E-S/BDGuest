package br.com.guest.restaurante_admin.entidades.reserva.mapper;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import br.com.guest.restaurante_admin.entidades.reserva.Reserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;

public class MapeadorReserva implements RowMapper<Reserva> {
    @Override
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
        //TODO [MID]: Continuar essa classe após ajeitar as mesas aq
        Integer id = rs.getInt("numero_id");
        Integer quantidadeCadeiras = rs.getInt("quantidade_cadeira");
        Mesa mesa = new Mesa(id, quantidadeCadeiras);

        String cpfPessoa = rs.getString("P.cpf");
        String nome = rs.getString("nome");
        String rua = rs.getString("rua");
        String bairro = rs.getString("bairro");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String cep = rs.getString("cep");
        String email = rs.getString("email");
        Date data_nascimento = rs.getDate("data_nascimento");
        String telefone = rs.getString("telefone");
        String telefone2 = rs.getString("telefone_2");
        Pessoa pessoa = new Pessoa(cpfPessoa, nome, rua, bairro, estado, cidade, cep, email, data_nascimento, telefone, telefone2);

        String cpf = rs.getString("C.cpf");
        Integer fidelidade = rs.getInt("fidelidade");
        String metodoPagamento1 = rs.getString("metodo_pagamento_1");
        String metodoPagamento2 = rs.getString("metodo_pagamento_2");
        Cliente cliente = new Cliente(pessoa, cpf, fidelidade, metodoPagamento1, metodoPagamento2);

        String cpfCliente = rs.getString("cpf_cliente");
        Date data = rs.getDate("data");
        Integer quantidadePessoas = rs.getInt("quantidade_pessoas");
        Integer id_mesa = rs.getInt("id_mesa");
        LocalTime horarioEntrada = rs.getTime("horario_entrada").toLocalTime();


        return new Reserva(cliente, mesa, cpfCliente, data, horarioEntrada, quantidadePessoas, id_mesa);

    }
}
