package br.com.guest.restaurante_admin.entidades.dashboard;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Object> pratoMaisVendido();
    Long quantidadePedidosRealizadosHoje();
    Double mediaDePedidosRealizadosPorGarcom();
    Map<String, Object> quantidadeDePratosServidosPorDia();
    Map<String, Object> quantidadeDeReservasPorDia();
    Map<String, Object> garconsPedidosAcimaDaMedia();
    Map<String, Object> produtosProximosAValidade();
    Map<String, Object> distribuicaoDosPedidosPorHoraDoDia();
}
