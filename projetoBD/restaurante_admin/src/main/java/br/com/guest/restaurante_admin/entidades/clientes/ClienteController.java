package br.com.guest.restaurante_admin.entidades.clientes;

import br.com.guest.restaurante_admin.entidades.pessoa.PessoaService;
import br.com.guest.restaurante_admin.execoes.CampoDeAlteracaoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.FiltroNaoDisponivelException;
import br.com.guest.restaurante_admin.execoes.PessoaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("")
    public ResponseEntity<String> salvarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.salvarCliente(cliente);
            return new ResponseEntity("Cliente salvo com sucesso!", HttpStatus.CREATED);
        }catch (PessoaNaoEncontradaException e) {
            return new ResponseEntity<>("Cpf: "+e.getMessage()+" não encontrado!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<Object> filtrarClientes(@PathVariable String filtro, @RequestParam String valor) {
        try{
            return new ResponseEntity<>(clienteService.buscarClientePorFiltro(filtro, valor), HttpStatus.OK);
        }catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{filtro}")
    public ResponseEntity<String> atualizarClientePorFiltro(@PathVariable String filtro, @RequestParam String valor, @RequestBody HashMap<String, Object> alteracoes) {
        try{
            clienteService.atualizarClientePorFiltro(filtro, valor,(String)alteracoes.get("campo"), (String)alteracoes.get("valor"));
            return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.NO_CONTENT);
        }catch (FiltroNaoDisponivelException e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }catch (CampoDeAlteracaoNaoEncontradoException e){
            return new ResponseEntity<>("Campo para alteração: "+e.getMessage()+" indisponível", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{filtro}")
    public ResponseEntity<String> deletarClientePorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        try{
            clienteService.deletarClientePorFiltro(filtro, valor);
            return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>("Filtro: "+e.getMessage()+" não disponível", HttpStatus.BAD_REQUEST);
        }
    }
}
