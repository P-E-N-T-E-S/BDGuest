package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/garcom")
public class GarcomController {

    private GarcomService garcomService;

    public GarcomController(GarcomService garcomService) {
        this.garcomService = garcomService;
    }

    @PostMapping("")
    public ResponseEntity<String> salvarGarcom(@RequestBody Garcom garcom) {
        try {
            garcomService.salvarGarcom(garcom);
            return new ResponseEntity<>("Garcom adicionado com sucesso!", HttpStatus.OK);
        }catch (FuncionarioNaoEncontradoException e) {
            return new ResponseEntity<>("Cpf: "+e.getMessage()+" não encontrado em funcionários", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Garcom>> listarGarcom() {
        return new ResponseEntity<>(garcomService.listarGarcoms(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Garcom> buscarGarcomPorCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(garcomService.buscarGarcomPorCpf(cpf), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<Object> buscarGarcomPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try{
            return new ResponseEntity<>(garcomService.buscarGarcomPorFiltro(filtro, valor), HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<String> atualizarGarcomPorCpf(@PathVariable String cpf, @RequestBody Garcom garcom) {
        garcomService.atualizarGarcomPorCpf(garcom, cpf);
        return new ResponseEntity<>("Garcom atualizado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> atualizarGarcomPorFiltro(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, String> alteracoes) {
        try {
            garcomService.atualizarGarcomPorFiltro(filtro, valor, alteracoes.get("campo"), alteracoes.get("valor"));
            return new ResponseEntity<>("Garcom atualizado com sucesso!", HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }catch (CampoDeAlteracaoNaoEncontradoException e) {
            return new ResponseEntity<>("Campo para alteração: "+e.getMessage()+" indisponível", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletarGarcomPorCpf(@PathVariable String cpf) {
        garcomService.removerGarcomPorCpf(cpf);
        return new ResponseEntity<>("Garcom deletado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> deletarGarcomPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        garcomService.removerGarcomPorFiltro(filtro, valor);
        return new ResponseEntity<>("Garcom deletado com sucesso!", HttpStatus.OK);
    }
}
