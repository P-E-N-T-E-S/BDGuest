package br.com.guest.restaurante_admin.entidades.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<String> salvarProduto(@RequestBody Produto produto) {
        produtoService.salvarProduto(produto);
        return new ResponseEntity<>("Produto salvo com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return new ResponseEntity<>(produtoService.listarProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{filtro}")
    public ResponseEntity<List<Produto>> buscarProdutoPorFiltro(@PathVariable String filtro, @RequestParam String valor) {
        return new ResponseEntity<>(produtoService.buscarProdutoPorFiltro(filtro, valor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerProdutoPorId(@PathVariable Integer id) {
        produtoService.removerProdutoPorId(id);
        return new ResponseEntity<>("Produto removido com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProduto(@RequestBody Produto produto, @PathVariable Integer id) {
        produtoService.atualizarProdutoPorId(produto, id);
        return new ResponseEntity<>("Produto alterado com sucesso!", HttpStatus.OK);
    }
}
