package br.com.guest.restaurante_admin.entidades.clientes.mapper;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import br.com.guest.restaurante_admin.entidades.pessoa.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClienteMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        String metodoPagamento1 = rs.getString("metodo_pagamento1");
        String metodoPagamento2 = rs.getString("metodo_pagamento2");
        return new Cliente(pessoa, cpf, fidelidade, metodoPagamento1, metodoPagamento2);
    }
}
