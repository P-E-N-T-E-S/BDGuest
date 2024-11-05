package br.com.guest.restaurante_admin.entidades.pedido.impl;

import br.com.guest.restaurante_admin.entidades.pedido.Pedido;
import br.com.guest.restaurante_admin.entidades.pedido.PedidoRepository;
import br.com.guest.restaurante_admin.entidades.pedido.mapper.MapeadorPedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private JdbcTemplate jdbcTemplate;

    public PedidoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void salvar(Pedido pedido, Integer idComanda) {
        String sql = "INSERT INTO Pedido (id_pedido, id_comanda, id_menu, horario, quantidade) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pedido.getIdPedido() ,idComanda, pedido.getIdPrato(), pedido.getHorario(), pedido.getQuantidade());
    }

    @Override
    public List<Pedido> listarPorComanda(Integer idComanda) {
        String sql = "SELECT * FROM Menu M join Pedido P on M.numero = P.id_comanda WHERE id_comanda = ?";
        return jdbcTemplate.query(sql, new MapeadorPedido(), idComanda);
    }

    @Override
    public List<Pedido> listarPorPrato(Integer idComanda, Integer idPrato) {
        String sql = "SELECT * FROM Menu M join Pedido P on M.numero = P.id_comanda WHERE id_comanda = ? AND id_menu = ?";
        return jdbcTemplate.query(sql, new MapeadorPedido(), idComanda, idPrato);
    }

    @Override
    public void excluirPedido(Pedido pedido, Integer idComanda) {
        String sql = "DELETE FROM Pedido WHERE id_comanda = ? AND id_menu = ? AND horario = ?";
        jdbcTemplate.update(sql, idComanda, pedido.getIdPrato(), pedido.getHorario());
    }

    @Override
    public void alterarPedido(Pedido pedido, Integer idComanda) {
        String sql = "UPDATE Pedido set id_menu = ?, horario = ?, quantidade = ? WHERE id_comanda = ? AND id_menu = ? AND horario = ?";
        jdbcTemplate.update(sql, pedido.getIdPrato(), pedido.getHorario(), pedido.getQuantidade(), idComanda, pedido.getIdPrato(), pedido.getHorario());
    }

    @Override
    public void desassociarPedidos(Integer idComanda) {
        String sql = "UPDATE Pedido set id_comanda = null WHERE id_comanda = ?";
        jdbcTemplate.update(sql, idComanda);
    }

    @Override
    public double calcularTotal(Integer idComanda) {
        String sql = "SELECT SUM(quantidade) FROM Pedido WHERE id_comanda = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, idComanda);
    }
}
