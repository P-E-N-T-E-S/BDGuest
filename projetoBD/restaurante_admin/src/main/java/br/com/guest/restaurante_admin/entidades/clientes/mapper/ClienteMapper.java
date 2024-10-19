package br.com.guest.restaurante_admin.entidades.clientes.mapper;

import br.com.guest.restaurante_admin.entidades.clientes.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        String cpf = rs.getString("cpf");
        Integer fidelidade = rs.getInt("fidelidade");
        String metodoPagamento1 = rs.getString("metodo_pagamento_1");
        String metodoPagamento2 = rs.getString("metodo_pagamento_2");
        return new Cliente(cpf, fidelidade, metodoPagamento1, metodoPagamento2);
    }
}
