package br.com.guest.restaurante_admin.entidades.comanda;

import br.com.guest.restaurante_admin.execoes.ClienteNaoCadastradoException;
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
        try {
            this.comandaService.salvarComanda(comanda);
            return new ResponseEntity<>("Comanda criada com sucesso", HttpStatus.CREATED);
        }catch (ClienteNaoCadastradoException e) {
            return new ResponseEntity<>("Comanda criada mas cliente não está cadastrado", HttpStatus.OK);
        }
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
    public ResponseEntity<String> deletarComandaPorId(@PathVariable Integer id) {
        comandaService.excluirComanda(id);
        return new ResponseEntity<>("Comanda Deletada com sucesso", HttpStatus.OK);
    }

    @PutMapping("/{id}/chamar")
    public ResponseEntity<String> chamarGarcom(@PathVariable Integer id) {
        comandaService.chamarGarcom(id);
        return new ResponseEntity<>("Garcom chamado", HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar-chamado")
    public ResponseEntity<String> cancelarChamado(@PathVariable Integer id) {
        comandaService.cancelarChamado(id);
        return new ResponseEntity<>("Garcom chamado", HttpStatus.OK);
    }
}
