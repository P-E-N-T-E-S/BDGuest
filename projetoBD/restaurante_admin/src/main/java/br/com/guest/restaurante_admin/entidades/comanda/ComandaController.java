package br.com.guest.restaurante_admin.entidades.comanda;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comanda")
public class ComandaController {

    private ComandaService comandaService;

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarComanda(@RequestBody Comanda comanda) {
        this.comandaService.salvarComanda(comanda);
        return new ResponseEntity<>("Comanda Cadastrada com sucesso", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> listarComandas() {
        return new ResponseEntity<>(comandaService.listarComandas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> buscarComandaPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(comandaService.buscarComandaPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarComanda(@RequestBody Comanda comanda, @PathVariable Integer id) {
        comandaService.alterarComanda(comanda, id);
        return new ResponseEntity<>("Comanda Atualizada com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarComandaPorId(@PathVariable String id) {
        comandaService.excluirComanda(id);
        return new ResponseEntity<>("Comanda Deletada com sucesso", HttpStatus.OK);
    }
}