package br.com.guest.restaurante_admin.entidades.dashboard;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Object> pratoMaisVendido();
    Long quantidadePedidosRealizadosHoje();
    Double lucroBrutoHoje();
    Map<String, Object> quantidadeDePratosServidosPorDia();
    Map<String, Object> quantidadeDeReservasPorDia();
    List<Double> distribuicaoDoPrecoDosPratos();
    Map<String, Object> produtosProximosAValidade();
    Map<String, Object> distribuicaoDosPedidosPorHoraDoDia();
}
