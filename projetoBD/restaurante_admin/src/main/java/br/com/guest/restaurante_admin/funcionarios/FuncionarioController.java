package br.com.guest.restaurante_admin.funcionarios;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
