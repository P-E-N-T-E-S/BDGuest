package br.com.guest.restaurante_admin.entidades.funcionarios;

import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.PessoaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("")
    public ResponseEntity<String> novoFuncionario(@RequestBody Funcionario funcionario) {
        try {
            funcionarioService.salvarFuncionario(funcionario);
            return new ResponseEntity<>("Funcionario Salvo com sucesso!", HttpStatus.CREATED);
        }catch (PessoaNaoEncontradaException e){
            return new ResponseEntity<>("CPF: "+e.getMessage()+" não encontrado no banco!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        return new ResponseEntity<>(funcionarioService.listarFuncionarios(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<Object> buscarFuncionarioPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try{
            return new ResponseEntity<>(funcionarioService.buscarFuncionarioPorFiltro(filtro, valor), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponìvel", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> atualizarFuncionarioPorFiltro(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, Object> alteracoes) {
        try{
            funcionarioService.atualizarFuncionarioPorFiltro(filtro, valor, (String)alteracoes.get("campo"), (String)alteracoes.get("valor"));
            return new ResponseEntity<>("Funcionário(s) alterados com sucesso", HttpStatus.NO_CONTENT);
        }catch (FiltroNaoDisponivelException e){
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }catch (CampoDeAlteracaoNaoEncontradoException e){
            return new ResponseEntity<>("Campo para alteração: "+e.getMessage()+" indisponível", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> apagarFuncionarioPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try {
            funcionarioService.deletarFuncionarioPorFiltro(filtro, valor);
            return new ResponseEntity<>("Funcionário(s) apagados com sucesso!", HttpStatus.NO_CONTENT);
        } catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+"indisponível", HttpStatus.BAD_REQUEST);
        }
    }
}
