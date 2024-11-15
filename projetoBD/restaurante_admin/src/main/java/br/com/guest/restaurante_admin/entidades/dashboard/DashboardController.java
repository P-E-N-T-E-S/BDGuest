package br.com.guest.restaurante_admin.entidades.dashboard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController { 

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/prato_mais_vendido")
    public ResponseEntity<Map<String, Object>> pratoMaisVendido() {
        return new ResponseEntity<>(dashboardService.pratoMaisVendido(), HttpStatus.OK);
    }

    @GetMapping("/pedidos_hoje")
    public ResponseEntity<Long> pedidosHoje() {
        return new ResponseEntity<>(dashboardService.quantidadePedidosRealizadosHoje(), HttpStatus.OK);
    }

    @GetMapping("/pratos_por_dia")
    public ResponseEntity<Map<String, Object>> pratosPorDia() {
        return new ResponseEntity<>(dashboardService.quantidadeDePratosServidosPorDia(), HttpStatus.OK);
    }

    @GetMapping("/media_garcom")
    public ResponseEntity<Double> mediaPedidosGarcom() {
        return new ResponseEntity<>(dashboardService.mediaDePedidosRealizadosPorGarcom(), HttpStatus.OK);
    }

    @GetMapping("/quantidade_reservas_dia")
    public ResponseEntity<Map<String, Object>> quantidadeReservasDia() {
        return new ResponseEntity<>(dashboardService.quantidadeDeReservasPorDia(), HttpStatus.OK);
    }

    @GetMapping("/garcons_acima_media")
    public ResponseEntity<Map<String, Object>> distribuicaoPrecoPratos() {
        return new ResponseEntity<>(dashboardService.garconsPedidosAcimaDaMedia(), HttpStatus.OK);
    }

    @GetMapping("/produtos_proximos_validade")
    public ResponseEntity<Map<String, Object>> produtosProximosAValidade() {
        return new ResponseEntity<>(dashboardService.produtosProximosAValidade(), HttpStatus.OK);
    }

    @GetMapping("/distribuicao_pedidos_hora")
    public ResponseEntity<Map<String, Object>> distribuicaoPedidosHora() {
        return new ResponseEntity<>(dashboardService.distribuicaoDosPedidosPorHoraDoDia(), HttpStatus.OK);
    }
}
