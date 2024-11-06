package br.com.guest.restaurante_admin.entidades.dashboard;

import java.util.List;
import java.util.Map;

public interface DashboardRepository {
    Map<String, Object> pratoMaisVendido();
    Map<String, Object> quantidadePedidosRealizadosHoje();
    Map<String, Object> lucroBrutoHoje();
    List<Map<String, Object>> quantidadeDePratosServidosPorDia();
    List<Map<String, Object>> quantidadeDeReservasPorDia();
    List<Map<String, Object>> distribuicaoDoPrecoDosPratos();
    List<Map<String, Object>> produtosProximosAValidade();
    List<Map<String, Object>> distribuicaoDosPedidosPorHoraDoDia();
}
