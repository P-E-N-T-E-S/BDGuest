package br.com.guest.restaurante_admin.pessoa;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaControler {

    private final PessoaService pessoaService;

    public PessoaControler(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("")
    public ResponseEntity<String> novaPessoa(@RequestBody Pessoa pessoa){
        pessoaService.salvarNovaPessoa(pessoa);
        return new ResponseEntity<>("Pessoa salvo com sucesso!",HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        return new ResponseEntity<>(pessoaService.listarPessoas(), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable String cpf){
        return new ResponseEntity<>(pessoaService.buscarPessoaPorCpf(cpf), HttpStatus.OK);
    }
}
