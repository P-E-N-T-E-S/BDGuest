package br.com.guest.restaurante_admin.entidades.contem.impl;

import br.com.guest.restaurante_admin.entidades.contem.Contem;
import br.com.guest.restaurante_admin.entidades.contem.ContemRepository;
import br.com.guest.restaurante_admin.entidades.contem.ContemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContemServiceImpl implements ContemService {

    private ContemRepository contemRepository;

    public ContemServiceImpl(ContemRepository contemRepository) {
        this.contemRepository = contemRepository;
    }

    @Override
    public void salvarContem(Contem contem) {
        contemRepository.salvarContem(contem);
    }

    @Override
    public List<Contem> buscarContemPorProduto(Integer idProduto) {
        return contemRepository.buscarContemPorProduto(idProduto);
    }

    @Override
    public List<Contem> buscarContemPorEstoque(Integer idEstoque) {
        return contemRepository.buscarContemPorEstoque(idEstoque);
    }

    @Override
    public void excluirContem(Contem contem) {
        contemRepository.excluirContem(contem);
    }
}
