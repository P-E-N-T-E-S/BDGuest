package br.com.guest.restaurante_admin.entidades.usa.impl;

import br.com.guest.restaurante_admin.entidades.produto.Produto;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoRepository;
import br.com.guest.restaurante_admin.entidades.produto.ProdutoService;
import br.com.guest.restaurante_admin.entidades.usa.Usa;
import br.com.guest.restaurante_admin.entidades.usa.UsaRepository;
import br.com.guest.restaurante_admin.entidades.usa.UsaService;
import br.com.guest.restaurante_admin.execoes.QuantidadeDeIngredienteNaoDisponivel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsaServiceImpl implements UsaService {

    private UsaRepository usaRepository;

    private ProdutoRepository produtoRepository;

    public UsaServiceImpl(UsaRepository usaRepository,  ProdutoRepository produtoRepository) {
        this.usaRepository = usaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void salvarUso(Usa usa) {
        //TODO: fazer as verificações aqui
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
    public void reduzirQuantidadePorPrato(Integer pratoId) {
        List<Produto> produtosFaltantes = produtoRepository.verificarQuantidadePorPrato(pratoId);
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
