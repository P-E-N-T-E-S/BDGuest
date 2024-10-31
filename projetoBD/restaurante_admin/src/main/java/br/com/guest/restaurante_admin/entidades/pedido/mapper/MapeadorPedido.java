package br.com.guest.restaurante_admin.entidades.pedido.mapper;

import br.com.guest.restaurante_admin.entidades.menu.Prato;
import br.com.guest.restaurante_admin.entidades.pedido.Pedido;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeadorPedido implements RowMapper<Pedido> {

    @Override
    public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
        String nome = rs.getString("nome");
        String imagem = rs.getString("imagem");
        String descricao = rs.getString("descricao");
        double preco = rs.getDouble("preco");
        int numeroPrato = rs.getInt("numero");
        Prato prato = new Prato(numeroPrato, nome, imagem, descricao, preco);
        Integer idPedido = rs.getInt("id_pedido");
        Integer idComanda = rs.getInt("id_comanda");
        Integer idPrato = rs.getInt("id_menu");
        LocalDateTime horario = rs.getTimestamp("horario").toLocalDateTime();
        Integer quantidade = rs.getInt("quantidade");

        return new Pedido(prato, idPedido, idComanda, idPrato, horario, quantidade);
    }
}
