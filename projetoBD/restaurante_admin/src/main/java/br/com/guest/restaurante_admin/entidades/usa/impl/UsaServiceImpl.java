package br.com.guest.restaurante_admin.entidades.usa.impl;

import br.com.guest.restaurante_admin.entidades.menu.PratoRepository;
import br.com.guest.restaurante_admin.entidades.menu.PratoService;
import br.com.guest.restaurante_admin.entidades.produto.Produto;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoRepository;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoService;
import br.com.guest.restaurante_admin.entidades.usa.Usa;
import br.com.guest.restaurante_admin.entidades.usa.UsaRepository;
import br.com.guest.restaurante_admin.entidades.usa.UsaService;
import br.com.guest.restaurante_admin.execoes.PratoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.ProdutoNaoEncontradoException;
import br.com.guest.restaurante_admin.execoes.QuantidadeDeIngredienteNaoDisponivel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsaServiceImpl implements UsaService {

    private UsaRepository usaRepository;
    private ProdutoService produtoService;

    public UsaServiceImpl(UsaRepository usaRepository, ProdutoService produtoService) {
        this.usaRepository = usaRepository;
        this.produtoService = produtoService;
    }

    @Override
    public void salvarUso(Usa usa) {
        if(produtoService.buscarProdutoPorId(usa.getProdutoId()) == null) {
            throw new ProdutoNaoEncontradoException(""+usa.getProdutoId());
        }
        usaRepository.salvarUso(usa);
    }

    @Override
    public List<Usa> getUsosPorProduto(Integer idProduto) {
        return usaRepository.getUsosPorProduto(idProduto);
    }

    @Override
    public List<Usa> getUsosPorPrato(Integer idPrato) {
        return usaRepository.getUsosPorPrato(idPrato);
    }

    @Override
    public void deletarUso(Integer idPrato, Integer idProduto) {
        usaRepository.deletarUso(idPrato, idProduto);
    }

    @Override
    public void deletarUsoPorPrato(Integer idPrato) {
        usaRepository.deletarUsoPorPrato(idPrato);
    }

    @Override
    public void reduzirQuantidadePorPrato(Integer pratoId) {
        List<Produto> produtosFaltantes = produtoService.verificarQuantidadePorPrato(pratoId);
        if(!produtosFaltantes.isEmpty()) {
            throw new QuantidadeDeIngredienteNaoDisponivel(produtosFaltantes.toString());
        }
        usaRepository.reduzirQuantidadePorPrato(pratoId);
    }

    @Override
    public void aumentarQuantidadePorPrato(Integer pratoId) {
        usaRepository.aumentarQuantidadePorPrato(pratoId);
    }
}
