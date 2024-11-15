package br.com.guest.restaurante_admin.entidades.dashboard;

import java.util.List;
import java.util.Map;

public interface DashboardRepository {
    Map<String, Object> pratoMaisVendido();
    Map<String, Object> quantidadePedidosRealizadosHoje();
    Double mediaDePedidosRealizadosPorGarcom();
    List<Map<String, Object>> quantidadeDePratosServidosPorDia();
    List<Map<String, Object>> quantidadeDeReservasPorDia();
    List<Map<String, Object>> garconsPedidosAcimaDaMedia();
    List<Map<String, Object>> produtosProximosAValidade();
    List<Map<String, Object>> distribuicaoDosPedidosPorHoraDoDia();
}
