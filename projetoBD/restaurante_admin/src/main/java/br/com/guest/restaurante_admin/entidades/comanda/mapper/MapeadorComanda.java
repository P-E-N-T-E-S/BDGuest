package br.com.guest.restaurante_admin.entidades.comanda.mapper;

import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.mesa.Mesa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeadorComanda implements RowMapper<Comanda> {
    @Override
    public Comanda mapRow(ResultSet rs, int rowNum) throws SQLException {
        //TODO: ajeitar aqui e os SQLs

        Integer idMesa = rs.getInt("M.numero_id");
        Integer quantidadeCadeiras = rs.getInt("quantidade_cadeira");

        Mesa mesa = new Mesa(idMesa, quantidadeCadeiras);

        Integer id = rs.getInt("C.numero_id");
        String cpfPessoa = rs.getString("cpf_pessoa");
        LocalDateTime dataCadastro = rs.getTimestamp("acesso").toLocalDateTime();
        String nomeCliente = rs.getString("nome_cliente");
        Integer mesaId = rs.getInt("mesa");
        return new Comanda(mesa, id, cpfPessoa, nomeCliente, dataCadastro, mesaId);
    }
}
