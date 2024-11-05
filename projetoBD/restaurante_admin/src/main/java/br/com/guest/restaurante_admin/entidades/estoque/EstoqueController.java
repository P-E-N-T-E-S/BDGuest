package br.com.guest.restaurante_admin.entidades.estoque;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("estoque")
public class EstoqueController {

    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<String> salvarEstoque(@RequestBody Estoque estoque) {
        estoqueService.salvarEstoque(estoque);
        return new ResponseEntity<>("Estoque salvo com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoque() {
        return new ResponseEntity<>(estoqueService.listarEstoque(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<List<Estoque>> listarEstoquePorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        return new ResponseEntity<>(estoqueService.buscarEstoquePorFiltro(filtro, valor), HttpStatus.OK);
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> atualizarEstoque(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, Object> alteracoes) {
        estoqueService.alterarEstoquePorFiltro(filtro, valor, (String) alteracoes.get("campo"), (String) alteracoes.get("valor"));
        return new ResponseEntity<>("Estoque alterado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> deletarEstoque(@PathVariable String filtro, @RequestParam String valor) {
        estoqueService.excluirEstoquePorFiltro(filtro, valor);
        return new ResponseEntity<>("Estoque excluido com sucesso", HttpStatus.OK);
    }
}
