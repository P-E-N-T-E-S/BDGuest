package br.com.guest.restaurante_admin.entidades.pedido;

import br.com.guest.restaurante_admin.execoes.IngredientesInsuficientesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comanda/{idComanda}/pedidos") //TODO:ajustar as urls daqui
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<String> salvarPedido(@PathVariable Integer idComanda, @RequestBody Pedido pedido) {
        try {
            pedidoService.salvar(pedido, idComanda);
            return new ResponseEntity<>("Pedido salvo com sucesso", HttpStatus.CREATED);
        }catch (IngredientesInsuficientesException e) {
            return new ResponseEntity<>("Ingrediente "+e.getMessage()+" não disponivel para esse prato", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Integer idComanda) {
        return new ResponseEntity<>(pedidoService.listarPorComanda(idComanda), HttpStatus.OK);
    }

    @GetMapping("/{idPrato}")
    public ResponseEntity<List<Pedido>> listarPedidoPorPrato(@PathVariable Integer idPrato, @PathVariable Integer idComanda) {
        return new ResponseEntity<>(pedidoService.listarPorPrato(idComanda, idPrato), HttpStatus.OK);
    }

    @GetMapping("/garcom")
    public ResponseEntity<List<Pedido>> listarPedidoGarcom(@RequestParam String cpfGarcom) {
        return new ResponseEntity<>(pedidoService.listarPorGarcom(cpfGarcom), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(pedidoService.buscarPorId(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> alterarPedido(@PathVariable Integer idComanda, @RequestBody Pedido pedido) {
        pedidoService.alterarPedido(pedido, idComanda);
        return new ResponseEntity<>("Pedido alterado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<String> excluirPedido(@PathVariable Integer idPedido) {
        pedidoService.excluirPedido(idPedido);
        return new ResponseEntity<>("Pedido removido com sucesso", HttpStatus.OK);
    }

    @PutMapping("/fechar")
    public ResponseEntity<Double> fecharPedido(@PathVariable Integer idComanda) {
        return new ResponseEntity<>(pedidoService.desassociarPedidos(idComanda), HttpStatus.OK);
    }

    @PutMapping("/{idPedido}/status")
    public ResponseEntity<String> alterarStatus(@PathVariable Integer idPedido, @RequestParam String status) {
        pedidoService.alterarStatus(idPedido, status);
        return new ResponseEntity<>("Status do pedido alterado com sucesso", HttpStatus.OK);
    }
}
