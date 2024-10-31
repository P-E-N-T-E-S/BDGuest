package br.com.guest.restaurante_admin.entidades.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comanda/{idComanda}/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<String> salvarPedido(@PathVariable Integer idComanda, @RequestBody Pedido pedido) {
        pedidoService.salvar(pedido, idComanda);
        return new ResponseEntity<>("Pedido salvo com sucesso", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Integer idComanda) {
        return new ResponseEntity<>(pedidoService.listarPorComanda(idComanda), HttpStatus.OK);
    }

    @GetMapping("/{idPrato}")
    public ResponseEntity<List<Pedido>> listarPedidoPorPrato(@PathVariable Integer idPrato, @PathVariable Integer idComanda) {
        return new ResponseEntity<>(pedidoService.listarPorPrato(idComanda, idPrato), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> alterarPedido(@PathVariable Integer idComanda, @RequestBody Pedido pedido) {
        pedidoService.alterarPedido(pedido, idComanda);
        return new ResponseEntity<>("Pedido alterado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> excluirPedido(@PathVariable Integer idComanda, @RequestBody Pedido pedido) {
        pedidoService.excluirPedido(pedido, idComanda);
        return new ResponseEntity<>("Pedido removido com sucesso", HttpStatus.OK);
    }

    @PutMapping("/fechar")
    public ResponseEntity<Double> fecharPedido(@PathVariable Integer idComanda) {
        return new ResponseEntity<>(pedidoService.desassociarPedidos(idComanda), HttpStatus.OK);
    }
}
