package br.com.guest.restaurante_admin.entidades.comanda.impl;

import br.com.guest.restaurante_admin.entidades.comanda.Comanda;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaRepository;
import br.com.guest.restaurante_admin.entidades.comanda.ComandaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComandaServiceImpl implements ComandaService {

    private ComandaRepository comandaRepository;

    public ComandaServiceImpl(ComandaRepository comandaRepository) {
        //TODO [HIGH]: Verificicar cpf dos clientes
        this.comandaRepository = comandaRepository;
    }

    @Override
    public void salvarComanda(Comanda comanda) {
        comandaRepository.salvarComanda(comanda);
    }

    @Override
    public Comanda buscarComandaPorId(Integer id) {
        return comandaRepository.buscarComandaPorId(id);
    }

    @Override
    public List<Comanda> listarComandas() {
        return comandaRepository.listarComandas();
    }

    @Override
    public void excluirComanda(String id) {
        comandaRepository.excluirComanda(id);
    }

    @Override
    public void alterarComanda(Comanda comanda, Integer id) {
        comandaRepository.alterarComanda(comanda, id);
    }
}
