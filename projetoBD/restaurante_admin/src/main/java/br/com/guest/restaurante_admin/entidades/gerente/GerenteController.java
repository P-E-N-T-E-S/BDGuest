package br.com.guest.restaurante_admin.entidades.gerente;

import br.com.guest.restaurante_admin.entidades.garcom.Garcom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerente")
public class GerenteController {
    private GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    @PostMapping
    public ResponseEntity<String> salvarGerente(@RequestBody Gerente gerente) {
        try{
            gerenteService.salvarNovoGerente(gerente);
            return new ResponseEntity<>("Gerente salvo com sucesso!",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Gerente> buscarGerentePorCpf(@PathVariable String cpf){
        return new ResponseEntity<>(gerenteService.buscarGerentePorCpf(cpf), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Gerente>> listarGerentes() {
        return new ResponseEntity<>(gerenteService.listarGerentes(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}/garcons")
    public ResponseEntity<List<Garcom>> listarGarcomPorGerente(@PathVariable String cpf){
        return new ResponseEntity<>(gerenteService.listarGarconsGerenciados(cpf), HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> excluirGerente(@PathVariable String cpf){
        try{
            gerenteService.deletarGerentePorCpf(cpf);
            return new ResponseEntity<>("Gerente deletado com sucesso!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
