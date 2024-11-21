package br.com.guest.restaurante_admin.entidades.dashboard.impl;

import br.com.guest.restaurante_admin.entidades.dashboard.DashboardRepository;
import br.com.guest.restaurante_admin.entidades.dashboard.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    private DashboardRepository dashboardRepository;

    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Map<String, Object> pratoMaisVendido() {
        return dashboardRepository.pratoMaisVendido();
    }

    @Override
    public Long quantidadePedidosRealizadosHoje() {
        return (Long)dashboardRepository.quantidadePedidosRealizadosHoje().get("vendas");
    }

    @Override
    public Double mediaDePedidosRealizadosPorGarcom() {
        return dashboardRepository.mediaDePedidosRealizadosPorGarcom();
    }

    @Override
    public Map<String, Object> quantidadeDePratosServidosPorDia() {
        List<Map<String, Object>> query = dashboardRepository.quantidadeDePratosServidosPorDia();
        List<Date> datas = new ArrayList<>();
        List<BigDecimal> vendas = new ArrayList<>();
        for (Map<String, Object> map : query) {
            datas.add((Date)map.get("dia"));
            vendas.add((BigDecimal)map.get("pratos_vendidos"));
        }
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("vendas", vendas);
        resposta.put("datas", datas);
        return resposta;
    }

    @Override
    public Map<String, Object> quantidadeDeReservasPorDia() {
        List<Map<String, Object>> query = dashboardRepository.quantidadeDeReservasPorDia();
        List<Date> datas = new ArrayList<>();
        List<Long> reservas = new ArrayList<>();
        for (Map<String, Object> map : query) {
            datas.add((Date)map.get("dia"));
            reservas.add((Long)map.get("reservas"));
        }
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("reservas", reservas);
        resposta.put("datas", datas);
        return resposta;
    }

    @Override
    public Map<String, Object> garconsPedidosAcimaDaMedia() {
        List<Map<String, Object>> query = dashboardRepository.garconsPedidosAcimaDaMedia();
        List<String> nome = new ArrayList<>();
        List<Long> pedidos = new ArrayList<>();
        for (Map<String, Object> map : query) {
            nome.add((String)map.get("nome"));
            pedidos.add((Long)map.get("p_realizados"));
        }
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("nome", nome);
        resposta.put("pedidos", pedidos);
        return resposta;
    }


    @Override
    public Map<String, Object> produtosProximosAValidade() {
        List<Map<String, Object>> query = dashboardRepository.produtosProximosAValidade();
        List<Long> diasFaltantes = new ArrayList<>();
        List<String> prato = new ArrayList<>();
        for (Map<String, Object> map : query) {
            prato.add((String)map.get("prato"));
            diasFaltantes.add((Long)map.get("dias_para_estragar") * -1);
        }
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("pratos", prato);
        resposta.put("dias_faltantes", diasFaltantes);
        return resposta;
    }

    @Override
    public Map<String, Object> distribuicaoDosPedidosPorHoraDoDia() {
        List<Map<String, Object>> query = dashboardRepository.distribuicaoDosPedidosPorHoraDoDia();
        List<Integer> hora = new ArrayList<>();
        List<Long> pedidos = new ArrayList<>();
        for (Map<String, Object> map : query) {
            hora.add((Integer)map.get("hora"));
            pedidos.add((Long)map.get("pedidos"));
        }
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("pedidos", pedidos);
        resposta.put("hora", hora);
        return resposta;
    }
}
