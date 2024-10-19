package br.com.guest.restaurante_admin.entidades.pessoa;

import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

//    @GetMapping("/{cpf}")
//    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable String cpf){
//        Pessoa pessoa = pessoaService.buscarPessoaPorCpf(cpf);
//        if (pessoa == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(pessoa, HttpStatus.OK);
//    }

//    @DeleteMapping("/{cpf}")
//    public ResponseEntity<String> deletarPessoa(@PathVariable String cpf){
//        if(pessoaService.deletarPessoaPorCpf(cpf)){
//            return new ResponseEntity<>("Pessoa deletada com sucesso!", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("CPF não encontrado", HttpStatus.NOT_FOUND);
//    }

//    @PutMapping("/{cpf}")
//    public  ResponseEntity<String> atualizarPessoa(@PathVariable String cpf, @RequestBody Pessoa pessoa){
//        if(pessoaService.atualizarPessoaPorCpf(cpf, pessoa)){
//            return new ResponseEntity<>("Pessoa atualizada com sucesso!", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("CPF não encontrado", HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/{filtro}")
    public ResponseEntity<List<Pessoa>> buscarPessoasFiltro(@PathVariable String filtro, @RequestParam String valor){
        //Perguntar a Gabi se vamos precisar filtra por mais de um valor :))))
        try{
            return new ResponseEntity<>(pessoaService.buscarPessoaPorFiltro(filtro, valor), HttpStatus.OK);
        } catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> deletarPessoaFiltro(@PathVariable String filtro, @RequestParam String valor){
        try{
            pessoaService.deletarPessoaPorFiltro(filtro, valor);
            return new ResponseEntity<>("Pessoas deletadas com sucesso!", HttpStatus.OK);
        } catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro não disponível",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{filtro}")
    ResponseEntity<String> atualizarPessoaFiltro(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, Object> alteracoes){
        try{ //Json vai vir em campo:coluna, valor:alteracao
            pessoaService.atualizarPessoaPorFiltro(filtro, valor, (String)alteracoes.get("campo"), (String)alteracoes.get("valor"));
            return new ResponseEntity<>("Pessoa atualizada com sucesso!", HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e) {
            //todo diferenciar as respostas por exception
            return new ResponseEntity<>("Filtro não encontrado",HttpStatus.NOT_FOUND);
        }
    }
}
