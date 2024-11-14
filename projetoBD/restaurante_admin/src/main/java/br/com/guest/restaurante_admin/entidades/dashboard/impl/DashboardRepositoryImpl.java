package br.com.guest.restaurante_admin.entidades.dashboard.impl;

import br.com.guest.restaurante_admin.entidades.dashboard.DashboardRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    private JdbcTemplate jdbcTemplate;

    public DashboardRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> pratoMaisVendido() {
        String sql = "SELECT M.nome as prato, SUM(P.quantidade) as vendas FROM Pedidos_log P JOIN Menu M on P.id_prato=M.numero GROUP BY M.nome ORDER BY SUM(P.quantidade) DESC LIMIT 1";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String, Object> quantidadePedidosRealizadosHoje() {
        String sql = "SELECT Count(*) as vendas FROM Pedidos_log WHERE DATE(horario_pedido) = DATE(NOW());";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String, Object> lucroBrutoHoje() {
        String sql = "SELECT SUM(ROUND((M.preco * PL.quantidade), 2)) as ganho FROM Pedidos_log PL JOIN Menu M on M.numero = PL.id_prato WHERE DATE(PL.horario_pedido) = DATE(NOW())";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public List<Map<String, Object>> quantidadeDePratosServidosPorDia() {
        String sql = "SELECT SUM(PL.quantidade) as pratos_vendidos, DATE(PL.horario_pedido) as dia FROM Pedidos_log PL GROUP BY DATE(PL.horario_pedido) ORDER BY DATE(PL.horario_pedido);";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> quantidadeDeReservasPorDia() {
        String sql = "SELECT COUNT(*) as reservas, R.data as dia FROM Reserva R GROUP BY R.data ORDER BY R.data";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> distribuicaoDoPrecoDosPratos() {
        String sql = "SELECT M.preco FROM Menu M ORDER BY M.preco";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> produtosProximosAValidade() {
        String sql = "SELECT P.nome as prato, DATEDIFF(P.validade, DATE(NOW())) as dias_para_estragar FROM Produto P ORDER BY DATEDIFF(P.validade, DATE(NOW())) LIMIT 10";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> distribuicaoDosPedidosPorHoraDoDia() {
        String sql = "SELECT HOUR(horario) as hora, COUNT(*) as pedidos FROM Pedido GROUP BY HOUR(horario)";
        return jdbcTemplate.queryForList(sql);
    }
}
