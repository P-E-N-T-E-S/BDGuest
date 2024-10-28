package br.com.guest.restaurante_admin.entidades.usa.impl;

import br.com.guest.restaurante_admin.entidades.usa.Usa;
import br.com.guest.restaurante_admin.entidades.usa.UsaRepository;
import br.com.guest.restaurante_admin.entidades.usa.UsaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsaServiceImpl implements UsaService {

    private UsaRepository usaRepository;

    public UsaServiceImpl(UsaRepository usaRepository) {
        this.usaRepository = usaRepository;
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
}
