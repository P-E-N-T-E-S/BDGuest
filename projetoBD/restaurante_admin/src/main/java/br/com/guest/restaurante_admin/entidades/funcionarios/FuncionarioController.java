package br.com.guest.restaurante_admin.entidades.funcionarios;

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
        if(funcionarioService.salvarFuncionario(funcionario)){
            return new ResponseEntity<>("Funcionario Salvo com sucesso!", HttpStatus.OK);
        }
        return new ResponseEntity<>("CPF não encontrado!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        return new ResponseEntity<>(funcionarioService.listarFuncionarios(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<List<Funcionario>> buscarFuncionarioPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try{
            return new ResponseEntity<>(funcionarioService.buscarFuncionarioPorFiltro(filtro, valor), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> atualizarFuncionarioPorFiltro(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, Object> alteracoes) {
        try{
            funcionarioService.atualizarFuncionarioPorFiltro(filtro, valor, (String)alteracoes.get("campo"), (String)alteracoes.get("valor"));
            return new ResponseEntity<>("Funcionário(s) alterados com sucesso", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Erro ao alterar", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> apagarFuncionarioPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try {
            funcionarioService.deletarFuncionarioPorFiltro(filtro, valor);
            return new ResponseEntity<>("Funcionário(s) apagados com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao apagar Funcionário", HttpStatus.BAD_REQUEST);
        }
    }
}
