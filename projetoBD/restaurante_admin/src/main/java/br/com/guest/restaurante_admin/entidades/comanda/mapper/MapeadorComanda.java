package br.com.guest.restaurante_admin.entidades.comanda.mapper;

import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeadorComanda implements RowMapper<Comanda> {
    @Override
    public Comanda mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("numero_id");
        String cpfPessoa = rs.getString("cpf_pessoa");
        LocalDateTime dataCadastro = rs.getTimestamp("acesso").toLocalDateTime();
        String nomeCliente = rs.getString("nome_cliente");
        return new Comanda(id, cpfPessoa, nomeCliente, dataCadastro);
    }
}
