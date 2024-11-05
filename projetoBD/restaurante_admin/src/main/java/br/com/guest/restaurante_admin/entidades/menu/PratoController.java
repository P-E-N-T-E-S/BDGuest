package br.com.guest.restaurante_admin.entidades.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prato")
public class PratoController {

    private PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @PostMapping
    public ResponseEntity<String> salvarPrato(@RequestBody Prato prato) {
        pratoService.salvarPrato(prato); //enviar json com a lista
        return new ResponseEntity<>("Prato salvo com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> buscarPratoPorId(@PathVariable Long id) {
        Prato prato = pratoService.buscarPratoPorId(id);
        return new ResponseEntity<>(prato, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Prato>> listarPratos() {
        return new ResponseEntity<>(pratoService.listarPratos(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<List<Prato>> buscarPratoPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        return new ResponseEntity<>(pratoService.buscarPratoPorFiltro(filtro, valor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerPratoPorId(@PathVariable Long id) {
        pratoService.removerPratoPorId(id);
        return new ResponseEntity<>("Prato removido com sucesso", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPrato(@RequestBody Prato prato, @PathVariable Long id) {
        pratoService.atualizarPrato(prato, id);
        return new ResponseEntity<>("Prato atualizado com sucesso", HttpStatus.OK);
    }
}
