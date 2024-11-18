package br.com.guest.restaurante_admin.entidades.estoquista;

import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/estoquista")
public class EstoquistaController {

    private EstoquistaService estoquistaService;

    public EstoquistaController(EstoquistaService estoquistaService) {
        this.estoquistaService = estoquistaService;
    }

    @PostMapping
    public ResponseEntity<String> salvarEstoquista(@RequestBody Estoquista estoquista){
        estoquistaService.salvarEstoquista(estoquista);
        return new ResponseEntity<>("Estoquista Salvo",HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Estoquista>> buscarEstoquistas(){
        return new ResponseEntity<>(estoquistaService.listarEstoquistas(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<Object> buscarEstoquistaPorFiltro(@PathVariable String filtro, @RequestParam String valor){
        try {
            return new ResponseEntity<>(estoquistaService.buscarEstoquistasPorFiltro(filtro, valor), HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e){
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> excluirEstoquista(@PathVariable String filtro, @RequestParam String valor){
        try {
            estoquistaService.excluirEstoquistaPorFiltro(filtro, valor);
            return new ResponseEntity<>("Estoquista(s) excluídos com sucesso", HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e){
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> alterarEstoquista(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String,Object> camposAlterado){
        try {
            estoquistaService.alterarEstoquistaPorFiltro(filtro, valor, (String)camposAlterado.get("campo"), (String)camposAlterado.get("valor"));
            return new ResponseEntity<>("Estoquista(s) alterado com sucesso", HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e){
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível",HttpStatus.BAD_REQUEST);
        }catch (CampoDeAlteracaoNaoEncontradoException e){
            return new ResponseEntity<>("Campo: "+e.getMessage()+" não disponível para alteração", HttpStatus.BAD_REQUEST);
        }
    }
}
