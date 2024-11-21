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
    public Double mediaDePedidosRealizadosPorGarcom() {
        String sql = "SELECT AVG(PE.pedidos) FROM (SELECT COUNT(*) as pedidos FROM Pedidos_log PL GROUP BY PL.cpf_garcom) as PE";
        return jdbcTemplate.queryForObject(sql, Double.class);
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
    public List<Map<String, Object>> garconsPedidosAcimaDaMedia() {
        String sql = "SELECT P.nome, COUNT(*) as p_realizados FROM Pedidos_log PL JOIN Garcom G on PL.cpf_garcom = G.cpf JOIN Funcionario F ON G.cpf = F.cpf JOIN Pessoa P on F.cpf = P.cpf GROUP BY P.nome HAVING COUNT(*) > (SELECT AVG(PE.pedidos) FROM (SELECT COUNT(*) as pedidos FROM Pedidos_log PL GROUP BY PL.cpf_garcom) as PE)";
        return jdbcTemplate.queryForList(sql);
    }


    @Override
    public List<Map<String, Object>> produtosProximosAValidade() {
        String sql = "SELECT P.nome as prato, DATEDIFF(DATE(NOW()), P.validade) as dias_para_estragar FROM Produto P ORDER BY DATEDIFF(P.validade, DATE(NOW())) LIMIT 10";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> distribuicaoDosPedidosPorHoraDoDia() {
        String sql = "SELECT HOUR(horario_pedido) as hora, COUNT(*) as pedidos FROM Pedidos_log GROUP BY HOUR(horario_pedido)";
        return jdbcTemplate.queryForList(sql);
    }
}
