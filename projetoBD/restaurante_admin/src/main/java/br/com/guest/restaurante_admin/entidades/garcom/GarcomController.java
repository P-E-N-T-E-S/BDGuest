package br.com.guest.restaurante_admin.entidades.garcom;

import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.FuncionarioNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.GarcomNaoEncontradoException;
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
        //no json vai ter que ter um campo de mesas atendidas
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

    @GetMapping("/{filtro}")
    public ResponseEntity<Object> buscarGarcomPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try{
            return new ResponseEntity<>(garcomService.buscarGarcomPorFiltro(filtro, valor), HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
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

    @PutMapping("/mesas")
    public ResponseEntity<String> atualizarMesasGarcom(@RequestBody HashMap<String, Object> alteracoes) {
        try {
            garcomService.atualizarMesas((List<Integer>) alteracoes.get("mesas"), (String) alteracoes.get("cpf"));
            return new ResponseEntity<>("Mesas atualizadas com sucesso!", HttpStatus.OK);
        }catch (GarcomNaoEncontradoException e) {
            return new ResponseEntity<>("Garcom de cpf: "+e.getMessage()+" não encontrado!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> deletarGarcomPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try {
            garcomService.removerGarcomPorFiltro(filtro, valor);
            return new ResponseEntity<>("Garcom deletado com sucesso!", HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }
}
